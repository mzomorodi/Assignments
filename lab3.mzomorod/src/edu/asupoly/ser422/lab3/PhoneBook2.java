package edu.asupoly.ser422.lab3;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type;

public class PhoneBook2 {
	
	public static PhoneEntry getEntry(String number) {
		
	}
	
	private Map<String, PhoneEntry> getBook() {
		HashMap<String,PhoneEntry> book = new HashMap<String, PhoneEntry>();
		ObjectMapper mapper = new ObjectMapper();
		TypeReference ref = new TypeReference<List<PhoneEntry>>() {};
		List<PhoneEntry> entries = mapper.readValue(file, ref);
		
		for (PhoneEntry entry : entries) {
			book.put(entry.getPhone(), entry);
		}
		
		return book;
	}
	
	public synchronized PhoneEntry getEntry(String number) throws BadRequestException {
		HashMap<String,PhoneEntry> book = getBook();
		if (number.length() != 10)
			throw new BadRequestException("Invalid phone number");
		return book.get(number);
	}
}