package edu.asupoly.ser422.lab3

import java.io.*;

public class PhoneBookManager {
	
	private static PhoneBookManager manager;
	private static HashMap<String, File> books;
	
	private PhoneBookManager() {
		books = new HashMap<String, File>();
	}
	
	public static PhoneBookManager getInstance() {
		if (manager == null) {
			manager = new PhoneBookManager();
		}
		
		return manager;
	}
	
}