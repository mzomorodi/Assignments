package mzomorod;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.parsers.*;
import javax.xml.transform.*;

import org.w3c.dom.*;
import java.net.*;
import java.nio.file.*;
import java.io.*;

public class UserData {
	
	private static Document readDoc(InputStream is) {
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(is);
			
		} catch (Exception e) {
			
			System.err.println(e);
			
		}
		
		//is.close();
		return doc;
		
	}
	
	public static synchronized User[] getUserList(InputStream is) {
		
		Document doc = readDoc(is);
		Element root = doc.getDocumentElement();
		root.normalize();
		User[] users = null;
		
		NodeList userNodes = root.getElementsByTagName("user");
		users = new User[userNodes.getLength()];
		
		for (int index = 0; index <userNodes.getLength(); index++) {
			Node node = userNodes.item(index);
			
			User user = new User();
			Element e = (Element) node;
			user.setFirstName(e.getElementsByTagName("firstname").item(0).getTextContent());
			user.setLastName(e.getElementsByTagName("lastname").item(0).getTextContent());
			
			NodeList languageNodes = e.getElementsByTagName("language");
			String[] languages = new String[languageNodes.getLength()];
			for (int i = 0; i < languageNodes.getLength(); i++) {
				languages[i] = languageNodes.item(i).getTextContent();
			}
			user.addLanguages(languages);
			
			NodeList dayNodes = e.getElementsByTagName("day");
			String[] days = new String[dayNodes.getLength()];
			for (int i = 0; i < dayNodes.getLength(); i++) {
				days[i] = dayNodes.item(i).getTextContent();
			}
			user.addDays(days);
			
			user.setState(e.getElementsByTagName("state").item(0).getTextContent());
			
			users[index] = user;
		}
		
		return users;
		
	}
	
	public static synchronized void addUser(User user, InputStream is, URL resourceUrl) {
		
		Document doc = readDoc(is);
		Element root = doc.getDocumentElement();
		root.normalize();
		NodeList userNodes = doc.getElementsByTagName("user");
		
		Node userNode = null;
		for (int index = 0; index < userNodes.getLength(); index++) {
			Node node = userNodes.item(index);
			Element e = (Element) node;
			
			if (user.getFirstName().equals(e.getElementsByTagName("firstname").item(0).getTextContent()) &&
				user.getLastName().equals(e.getElementsByTagName("lastname").item(0).getTextContent())) {
				
				userNode = node;
				index = userNodes.getLength();
			}
		}
		
		// If user is found, then it is removed and replaced == updated
		if (userNode != null) {
			root.removeChild(userNode);
		}
		
		Element newUser = doc.createElement("user");
			
		Element firstName = doc.createElement("firstname");
		firstName.setTextContent(user.getFirstName());
		newUser.appendChild(firstName);
		
		Element lastName = doc.createElement("lastname");
		lastName.setTextContent(user.getLastName());
		newUser.appendChild(lastName);
		
		String[] languages = user.getLanguages();
		for (int i = 0; i < languages.length; i++) {
			Element language = doc.createElement("language");
			language.setTextContent(languages[i]);
			newUser.appendChild(language);
		}
		
		String days[] = user.getDays();
		for (int i = 0; i < days.length; i++) {
			Element day = doc.createElement("day");
			day.setTextContent(days[i]);
			newUser.appendChild(day);
		}
		
		Element state = doc.createElement("state");
		state.setTextContent(user.getState());
		newUser.appendChild(state);
		
		root.appendChild(newUser);
		
		writeDoc(doc, resourceUrl);
		
	}
	
	private static void writeDoc(Document doc, URL resourceUrl) {
		
		File file;
		TransformerFactory transformerFactory;
		Transformer transformer;
		DOMSource domSource;
		StreamResult streamResult;
		
		try {
			
			file = Paths.get(resourceUrl.toURI()).toFile();
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			domSource = new DOMSource(doc);
			streamResult = new StreamResult(file);
			
			transformer.transform(domSource, streamResult);
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}