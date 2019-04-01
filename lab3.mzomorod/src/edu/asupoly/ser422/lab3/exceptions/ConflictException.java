package edu.asupoly.ser422.lab3.exceptions;

public class ConflictException extends Exception {
	
	private String details;
	
	public ConflictException(String str) {
		details = str;
	}
	
	public String toString() {
		return "ConflictException[" + details + "]";
	}
}