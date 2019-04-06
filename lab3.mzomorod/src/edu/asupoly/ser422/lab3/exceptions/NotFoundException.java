package edu.asupoly.ser422.lab3.exceptions;

public class NotFoundException extends Exception {
	
	private String details;
	
	public NotFoundException(String str) {
		details = str;
	}
	
	public String toString() {
		return "NotFoundException[" + details + "]";
	}
}