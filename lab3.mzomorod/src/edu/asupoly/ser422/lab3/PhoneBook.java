package edu.asupoly.ser422.lab3;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;

import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;

import edu.asupoly.ser422.lab3.exceptions.*;

public class PhoneBook {
	
	private static PhoneBook pmanager;
	private static HashMap<String,PhoneEntry> pbook;
	
	private PhoneBook() {}
	
	/**
	 *	Returns a PhoneBook as a singleton to be shared
	 *	among the resource classes.
	 *
	 *	@return this PhoneBook, or a new instance if null.
	 */
	public static synchronized PhoneBook getInstance() {
		if (pmanager == null) {
			pmanager = new PhoneBook();
			pbook = new HashMap<String,PhoneEntry>();
			try {
				pmanager.loadBook();
			} catch (Exception e) {
				System.out.println("Book initialization failed.");
			}
		}
		
		return pmanager;
	}
	
	private void loadBook() throws IOException, URISyntaxException {
		File file = Paths.get(getClass().getResource("phonebook.txt").toURI()).toFile();
		TypeReference ref = new TypeReference<LinkedList<PhoneEntry>>() { };
		LinkedList<PhoneEntry> entries = new ObjectMapper().readValue(file, ref);
		
		for (PhoneEntry entry : entries) {
			pbook.put(entry.getPhone(), entry);
		}
	}
	
	private void saveBook() {
		LinkedList<PhoneEntry> entries = new LinkedList<PhoneEntry>(pbook.values());
		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = Paths.get(getClass().getResource("phonebook.txt").toURI()).toFile();
			mapper.writeValue(file, entries);
		} catch(Exception e) {
			System.out.println("Book save failed.");
		}
		
	}
	/**
	 *	(1) Provide an ability to retrieve a specific
	 *	PhoneEntry based on a phone number.
	 *
	 *	@param phone the number to search for
	 *	@return the PhoneEntry, if found, or null
	 *	@throws BadRequestException if the number is not 10 digits
	 *	@throws NotFoundException if no such PhoneEntry exists
	 */
	
	public synchronized PhoneEntry getEntry(String phone)
		throws BadRequestException, NotFoundException {
		
		if (phone.length() != 10)
			throw new BadRequestException("Invalid phone number");
		
		PhoneEntry entry = pbook.get(phone);
		
		if (entry == null) {
			throw new NotFoundException("No entry found");
		}
		
		return entry;
	}
		
	/**
	 *	(2) Provide an ability to create a new PhoneEntry.
	 *	Initially the PhoneEntry should be unlisted.
	 *	(meaning not in a PhoneBook). This should error
	 *	if a PhoneEntry with the same phone number already
	 *	exists in any PhoneBook or is unlisted.
	 *
	 *	@param entry the PhoneEntry to be added
	 *	@throws BadRequestException if an input is invalid
	 *	@throws ConflictException if an entry with the number
	 *			already exists
	 */
	public synchronized void createEntry(PhoneEntry entry)
		throws BadRequestException, ConflictException {
		
		if (entry.getPhone() == null || entry.getPhone().length() != 10) {
			throw new BadRequestException("Invalid phone number");
		}
		if (entry.getFirstName() == null || entry.getFirstName().isEmpty() ||
			entry.getLastName() == null || entry.getLastName().isEmpty()) {
			throw new BadRequestException("Invalid first or last name");
		}
		
		if (pbook.containsKey(entry.getPhone())) {
			throw new ConflictException("Number already in use");
		}
		
		entry.setBook("");
		pbook.put(entry.getPhone(), entry);
		
		saveBook();
	}
		
	/**
	 *	(3) Provide an ability to move a PhoneEntry to
	 *	a specific PhoneBook or make it unlisted. Ignores
	 *	firstname and lastname attributes of the parameter,
	 *	as the entry is assumed to exist in the phonebook.
	 *	If the entry does not exist, none are created. To
	 *	unlist an entry, omit the phone field of the entry
	 *	parameter.
	 *	
	 *	@param entry the PhoneEntry to move
	 *	@throws BadRequestException if an input is invalid
	 *	@throws NotFoundException if there is no corresponding entry
	 */
	public synchronized void moveEntry(PhoneEntry entry)
		throws BadRequestException, NotFoundException {
		
		if (entry.getPhone() == null || entry.getPhone().length() != 10) {
			throw new BadRequestException("Invalid phone number"); 
		}
		
		if (pbook.containsKey(entry.getPhone())) {
			PhoneEntry e = pbook.get(entry.getPhone());
			if (entry.getBook().isEmpty())
				e.setBook("");
			else
				e.setBook(entry.getBook());
		} else {
			throw new NotFoundException("No existing entry found");
		}
		
		saveBook();
	}
	
	/**
	 *	(4) Provide an ability to update a PhoneEntry’s phone
	 *	number. This should error if a PhoneEntry with the
	 *	same phone number already exists in any PhoneBook or
	 *	is unlisted.
	 *
	 *	@param entry the PhoneEntry to update
	 *	@throws ConflictException if an entry with the number
	 *			already exists
	 *	@throws BadRequestException if an input is invalid
	 *	@throws NotFoundException if there is no corresponding entry
	 */
	public synchronized void updateEntry(PhoneEntry entry)
		throws BadRequestException, ConflictException, NotFoundException {
		
		if (entry.getPhone() == null || entry.getPhone().length() != 10) {
			throw new BadRequestException("Invalid phone number");
		}
		if (entry.getFirstName() == null || entry.getFirstName().isEmpty() ||
			entry.getLastName() == null || entry.getLastName().isEmpty()) {
			throw new BadRequestException("Invalid first or last name");
		}
		if (entry.getBook().isEmpty()) {
			throw new BadRequestException("Entry cannot be unlisted");
		}
		
		if (pbook.containsKey(entry.getPhone())) {
			throw new ConflictException("Phone number belongs to existing entry");
		}
		
		LinkedList<PhoneEntry> entries = new LinkedList<PhoneEntry>(pbook.values());
		PhoneEntry pEntry = null;
		for (PhoneEntry pe : entries) {
			if (pe.getFirstName().equals(entry.getFirstName()) &&
				pe.getLastName().equals(entry.getLastName()) &&
				pe.getBook().equals(entry.getBook())) {
					
				pEntry = pe;
			}	
		}
		if (pEntry == null) {
			throw new NotFoundException("No existing entry found");
		} else {
			pEntry.setPhone(entry.getPhone());
		}
		
		saveBook();
	}
	
	/**
	 *	(5) Provide an ability to remove a PhoneEntry (and remove
	 *	it from a PhoneBook if it is listed in one) from the system.
	 *
	 *	@param phone the phone number of the entry to remove
	 *	@return	the entry removed, or null if non-existent
	 *	@throws BadRequestException if an input is invalid
	 *	@throws NotFoundException if the entry no longer exists
	 */
	public synchronized void deleteEntry(String phone)
		throws BadRequestException, NotFoundException {
		
		if (phone.length() != 10) {
			throw new BadRequestException("Invalid phone number");
		}
		
		if (pbook.remove(phone) == null) {
			throw new NotFoundException("No existing entry found");
		}
		
		saveBook();
	}
	
	/**
	 *	(6) Provide the ability to retrieve a specific PhoneBook and
	 *	all entries within the PhoneBook. Use bid == "" to retrieve
	 *	all unlisted entries.
	 *
	 *  (7) Provide an ability to retrieve a subset of the PhoneEntry
	 *	resources in a PhoneBook based on a substring of the lastname,
	 *	or the area code (1st 3 digits) of a phone number, or both in
	 *	the same query.
	 *
	 *	@param pid the phonebook id to retrieve
	 *	@param area the area code to filter
	 *	@param lname the last name to filter
	 *	@return list of entries belonging to the phonebook, possibly empty
	 *	@throws BadRequestException if an input is invalid
	 */
	public LinkedList<PhoneEntry> getPhoneBook(String bid, String area,
		String lname) throws BadRequestException {
		
		if (area != null && area.length() != 3) {
			throw new BadRequestException("Invalid area code");
		}
		
		LinkedList<PhoneEntry> entries = new LinkedList<PhoneEntry>(pbook.values());
		LinkedList<PhoneEntry> results = new LinkedList<PhoneEntry>();
		
		for (PhoneEntry entry : entries) {
			if (entry.getBook().equals(bid))
				results.add(entry);
		}
		entries = results;
		
		if (area != null) {
			results = new LinkedList<PhoneEntry>();
			for (PhoneEntry entry : entries) {
				if (entry.getPhone().substring(0,3).equals(area))
					results.add(entry);
			}
			entries = results;
		}
		
		if (lname != null && !lname.isEmpty()) {
			results = new LinkedList<PhoneEntry>();
			for (PhoneEntry entry : entries) {
				if (entry.getLastName().equals(lname))
					results.add(entry);
			}
			entries = results;
		}
		
		return entries;
	}
	
	/**
	 *	(8) Provide an ability to remove a PhoneBook only if it has no
	 *	PhoneEntries. If it has PhoneEntries an appropriate error should
	 *	be the response.
	 *
	 *	@return true if the phonebook is empty, false otherwise
	 */
	public boolean deletePhoneBook(String bid)
		throws BadRequestException {
		
		LinkedList<PhoneEntry> results = getPhoneBook(bid, null, null);
		
		return (results.size() == 0);
	}
}