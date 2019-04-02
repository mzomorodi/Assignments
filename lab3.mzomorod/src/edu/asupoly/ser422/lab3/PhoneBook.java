package edu.asupoly.ser422.lab3;

import java.io.*;
import java.util.*;

import edu.asupoly.ser422.lab3.exceptions.*;

public class PhoneBook {
	
	private static PhoneBook pmanager;
	
	private Map<String, PhoneEntry> pbook = new HashMap<String, PhoneEntry>();
	
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
		}
		
		return pmanager;
	}
	
	public boolean contains(String phone) {
		return pbook.containsKey(phone);
	}
	
	public int size() {
		return pbook.size();
	}
	
	public LinkedList<PhoneEntry> getEntries() {
		return ((LinkedList<PhoneEntry>) pbook.values());
	}
	
	/**
	 *	(1) Provide an ability to retrieve a specific
	 *	PhoneEntry based on a phone number.
	 *
	 *	@param phone the number to search for
	 *	@return the PhoneEntry, if found, or null
	 *	@throws BadRequestException if the number is not 10 digits
	 */
	public PhoneEntry getEntry(String phone) throws BadRequestException {
		if (phone.length() != 10)
			throw new BadRequestException("Invalid phone number");
		return pbook.get(phone);
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
	public void createEntry(PhoneEntry entry)
		throws BadRequestException, ConflictException {
		if (contains(entry.getPhone())) {
			throw new ConflictException("Phone number belongs to existing entry");
		}
		if (entry.getPhone() == null || entry.getPhone().length() != 10) {
			throw new BadRequestException("Invalid phone number");
		}
		if (entry.getFirstName() == null || entry.getFirstName().isEmpty() ||
			entry.getLastName() == null || entry.getLastName().isEmpty()) {
			throw new BadRequestException("Invalid first or last name");
		}
		
		pbook.put(entry.getPhone(), entry);
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
	public void moveEntry(PhoneEntry entry)
		throws BadRequestException, NotFoundException {
		if (entry.getPhone() == null || entry.getPhone().length() != 10) {
			throw new BadRequestException("Invalid phone number"); 
		}
		if (contains(entry.getPhone())) {
			PhoneEntry e = getEntry(entry.getPhone());
			if (entry.getBookID().isEmpty())
				e.unlist();
			else
				e.setBookID(entry.getBookID());
		} else {
			throw new NotFoundException("No existing entry found");
		}
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
	public void updateEntry(PhoneEntry entry)
		throws ConflictException, BadRequestException, NotFoundException {
		
		if (entry.getPhone() == null || entry.getPhone().length() != 10) {
			throw new BadRequestException("Invalid phone number");
		}
		if (entry.getFirstName() == null || entry.getFirstName().isEmpty() ||
			entry.getLastName() == null || entry.getLastName().isEmpty()) {
			throw new BadRequestException("Invalid first or last name");
		}
		if (contains(entry.getPhone())) {
			throw new ConflictException("Phone number belongs to existing entry");
		}
		if (entry.getBookID().isEmpty()) {
			throw new BadRequestException("Entry cannot be unlisted");
		}
		
		LinkedList<PhoneEntry> entries = getEntries();
		PhoneEntry pEntry = null;
		for (PhoneEntry pe : entries) {
			if (pe.getFirstName().equals(entry.getFirstName()) &&
				pe.getLastName().equals(entry.getLastName()) &&
				pe.getBookID().equals(entry.getBookID())) {
					
				pEntry = pe;
			}	
		}
		if (pEntry == null) {
			throw new NotFoundException("No existing entry found");
		} else {
			pEntry.setPhone(entry.getPhone());
		}
	}
	
	/**
	 *	(5) Provide an ability to remove a PhoneEntry (and remove
	 *	it from a PhoneBook if it is listed in one) from the system.
	 *
	 *	@param phone the phone number of the entry to remove
	 *	@return	the entry removed, or null if non-existent
	 *	@throws BadRequestException if an input is invalid
	 */
	public PhoneEntry remove(String phone) throws BadRequestException {
		
		if (phone.length() != 10) {
			throw new BadRequestException("Invalid phone number");
		} else {
			return pbook.remove(phone);
		}
	}
	
	/**
	 *	(6) Provide the ability to retrieve a specific PhoneBook and
	 *	all entries within the PhoneBook. Use bid == "" to retrieve
	 *	all unlisted entries.
	 *
	 *	@param pid the phonebook id to retrieve
	 *	@return list of entries belonging to the phonebook, possibly empty
	 *	@throws BadRequestException if an input is invalid
	 */
	public List<PhoneEntry> getPhoneBook(String bid) throws BadRequestException {
		
		if (bid == null) {
			throw new BadRequestException("Invalid book id");
		}
		List<PhoneEntry> results = new LinkedList<PhoneEntry>();
		LinkedList<PhoneEntry> entries = getEntries();
		
		for (PhoneEntry entry : entries) {
			if (entry.getBookID().equals(bid))
				results.add(entry);
		}
		
		return results;
	}
	
	/**
	 * (7) Provide an ability to retrieve a subset of the PhoneEntry
	 *	resources in a PhoneBook based on a substring of the lastname,
	 *	or the area code (1st 3 digits) of a phone number, or both in
	 *	the same query.
	 *
	 *	@param pid the phonebook to filter
	 *	@param area the area code to filter
	 *	@param lname the last name to filter
	 *	@return list of matching phonebook entries, possibly empty
	 *	@throws BadRequestException if an input is invalid
	 */
	public List<PhoneEntry> filter(String bid, String area, String lname)
		throws BadRequestException {
			
		if (bid == null) {
			throw new BadRequestException("Invalid book id");
		}
		if (area != null && area.length() != 3) {
			throw new BadRequestException("Invalid area code");
		}
		if (lname != null && lname.isEmpty()) {
			throw new BadRequestException("Invalid last name");
		}
		List<PhoneEntry> results = new LinkedList<PhoneEntry>();
		LinkedList<PhoneEntry> entries = (LinkedList<PhoneEntry>) getPhoneBook(bid);
		
		if ((area == null || area.isEmpty()) &&
			(lname == null || lname.isEmpty())) {
			
			results = entries;
		}  else if (area == null || area.isEmpty()) {
			for (PhoneEntry entry : entries) {
				if (lname.equals(entry.getLastName()))
					results.add(entry);
			}
		} else if (lname == null || lname.isEmpty()) {
			for (PhoneEntry entry : entries) {
				if (area.equals(entry.getPhone().substring(0, 4)))
					results.add(entry);
			}
		} else {
			for (PhoneEntry entry : entries) {
				if (lname.equals(entry.getLastName()) &&
					area.equals(entry.getPhone().substring(0, 4)))
					results.add(entry);
			}
		}
		
		return results;
	}
	
	/**
	 *	(8) Provide an ability to remove a PhoneBook only if it has no
	 *	PhoneEntries. If it has PhoneEntries an appropriate error should
	 *	be the response.
	 *
	 *	@return true if the phonebook is empty, false otherwise
	 */
	public boolean removePhoneBook(String bid)
		throws BadRequestException {
			
		LinkedList<PhoneEntry> results = (LinkedList<PhoneEntry>) getPhoneBook(bid);
		return (results.size() == 0);
	}
}