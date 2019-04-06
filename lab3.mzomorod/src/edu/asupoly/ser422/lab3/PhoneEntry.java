package edu.asupoly.ser422.lab3;

public class PhoneEntry {
	
	private String phone;
	private String firstname;
    private String lastname;
	private String book;
	
	public PhoneEntry() {}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getFirstName() {
		return this.firstname;
	}
	
	public String getLastName() {
		return this.lastname;
	}
	
	public String getBook() {
		return this.book;
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
	
	public void setBook(String b) {
		this.book = b;
	}
}