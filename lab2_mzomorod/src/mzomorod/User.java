package mzomorod;

import java.util.*;

public class User {
	
	
	private String firstName;
	private String lastName;
	private LinkedList<String> languages;
	private LinkedList<String> days;
	private String state;
	
	public User() {
		languages = new LinkedList<String>();
		days = new LinkedList<String>();
	}
	
	public void setFirstName(String fname) {
		firstName = fname;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lname) {
		lastName = lname;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void addLanguages(String[] lans) {
		languages.clear();
		if (lans.length >= 1) {
			for (String lan : lans)
				languages.add(lan);
		}
	}
	
	public String[] getLanguages() {
		return languages.toArray(new String[0]);
	}
	
	public void clearLanguages() {
		languages.clear();
	}
	
	public void addDays(String[] dArray) {
		days.clear();
		if (dArray.length >= 1) {
			for (String d : dArray)
				days.add(d);
		}
	}
	
	public String[] getDays() {
		return days.toArray(new String[0]);
	}
	
	public void clearDays() {
		days.clear();
	}
	
	public void setState(String s) {
		state = s;
	}
	
	public String getState() {
		return state;
	}
}