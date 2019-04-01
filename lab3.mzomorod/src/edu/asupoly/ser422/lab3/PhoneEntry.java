package edu.asupoly.ser422.lab3;

public class PhoneEntry {
	
	private String phone;
	private String firstname;
    private String lastname;
	private String bookid;
	
	public PhoneEntry() {
		this.bookid = "";
	}
	
	public PhoneEntry(String phone, String fname, String lname) {
		this(phone, fname, lname, "");
	}
	
	public PhoneEntry(String phone, String fname, String lname, String id) {
		this.phone = phone;
		this.firstname  = fname;
		this.lastname  = lname;
		this.bookid = id;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getFirstName() {
		return this.firstname;
	}
	
	public String getLastName() {
		return this.lastname;
	}
	
	public String getBookID() {
		return this.bookid;
	}
	
	public void setPhone(String p) {
		this.phone = p;
	}
	
	public void setFirstName(String f) {
		this.firstname = f;
	}
	
	public void setLastName(String l) {
		this.lastname = l;
	}
	
	public void setBookID(String id) {
		this.bookid = id;
	}
	
	public void unlist() {
		this.bookid = "";
	}
}