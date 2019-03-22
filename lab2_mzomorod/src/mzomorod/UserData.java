package mzomorod;

import javax.xml.parsers.DocumentBulderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.*;

import java.io.*;

public class UserData {
	
	private Document xmlDom;
	
	public UserData() {
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document xmlDom = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList users = doc.getElementsByTagName("user");
		
		for (int index = 0; index < users.getLength(); index++) {
			Node user = users.item(index);
			System.out.println("Element Type: " + user.getNodeName());
			
			if (user.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) user;
				System.out.println("ID Attribute: " + e.getAttribute("id"));
				
				System.out.println("First Name: " +
					e.getElementsByTagName("firstname").item(0).getTextContent());
				System.out.println("Last Name: " +
				e.getElementsByTagName("lastname").item(0).getTextContent());
				
				NodeList languages = e.getElementsByTagName("languages");
			}
		}
	}
	
	public Element getUserNode(String fname, String lname) {
		for (int index = 0; index < users.getLength(); index++) {
			Node user = users.item(0);
			Element e;
			if (user.getNodeType() == Node.ELEMENT_NODE) {
				e = (Element) user;
			}
			
			if (e.getElementsByTagName("firstname").item(0).getTextContent().equals(fname) &&
				e.getElementsByTagName("lastname").item(0).getTextContent().equals(lname)) {
				
				return e;
			}
		}
	}
}