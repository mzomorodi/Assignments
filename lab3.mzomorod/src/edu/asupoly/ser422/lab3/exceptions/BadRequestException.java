package edu.asupoly.ser422.lab3.exceptions;

public class BadRequestException extends Exception {
	
	private String details;
	
	public BadRequestException(String str) {
		details = str;
	}
	
	public String toString() {
		return "BadRequestException[" + details + "]";
	}
}