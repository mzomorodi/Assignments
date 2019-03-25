package mzomorod;

import javax.xml.parsers.DocumentBulderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.parsers.*;
import javax.xml.transform.*;

import org.w3c.dom.*;
import java.net.*;
import java.io.*;

public class UserData {
	
	public static synchronized User[] getUserList(String filename) throws IOException {
		
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(is);
		doc.getDocumentElement().normalize();
		NodeList userNodes = doc.getElementsByTagName("user");
		User[] users = new User[userNodes.getLength()];
		
		for (int index = 0; index <userNodes.getLength(); index++) {
			Node node = userNodes.item(index);
			
			User user = new User();
			Element e = (Element) user;
			user.setFirstName(e.getElementsByTagName("firstname").item(0).getTextContent());
			user.setLastName(e.getElementsByTagName("lastname").item(0).getTextContent());
			
			NodeList languageNodes = e.getElementsByTagName("languages");
			String[] languages = new String[languageNodes.getLength()];
			for (int i = 0; i < languageNodes.getLength(); i++) {
				languages[i] = languageNodes[i].getTextContent();
			}
			user.addLanguages(languages);
			
			NodeList dayNodes = e.getElementsByTagName("days");
			String[] days = new String[dayNodes.getLength()];
			for (int i = 0; i < dayNodes.getLength(); i++) {
				days[i] = dayNodes[i].getTextContent();
			}
			user.addDays(days);
			
			user.setState(e.getElementsByTagName("state").item(0).getTextContent());
			
			users[index] = user;
		}
		
		is.close();
		return users;
		
	}
	
	public static synchronized void addUser(User user, String filename) 
		throws TransformerFactoryConfigurationError, TransformerConfigurationError{
		
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(is);
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
		
		// If user is found, then it is removed first, then replaced == updated
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
		
		URL resourceUrl = getClass().getResource(fname);
		File file;
		
		try {
			file = Paths.get(resourceUrl.toURI()).toFile();
		} catch (InvalidPathException ipe) {
			
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource domSource = new DOMSource(doc);
		StreamResult streamResult = new StreamResult(file);
		
		try {
			transformer.transform(domSource, streamResult);
		} catch (TransformerException te) {
			
		}
		
		
	}	
}