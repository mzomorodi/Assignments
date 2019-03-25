package mzomorod;

import java.util.*;

public class User {
	
	
	private String firstName;
	private String lastName;
	private LinkedList<String> languages;
	private LinkedList<String> days;
	private String state;
	
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
	
	public void addLanguage(String lan) {
		languages.add(lan);
	}
	
	public boolean removeLanguage(String lan) {
		return languages.remove(lan);
	}
	
	public void addLanguages(String[] lans) {
		languages.clear();
		for (String lan : lans)
			languages.add(lan);
	}
	
	public String[] getLanguages() {
		return languages.toArray(new String[0]);
	}
	
	public void addDay(String d) {
		days.add(d);
	}
	
	public boolean removeDay(String d) {
		return days.remove(d);
	}
	
	public void addDays(String[] dArray) {
		days.clear();
		for (String d : dArray)
			days.add(d);
	}
	
	public String[] getDays() {
		return days.toArray(new String[0]);
	}
	
	public void setState(String s) {
		state = s;
	}
	
	public String getState() {
		return state;
	}
}