package edu.asupoly.ser422.lab3.exceptions;

public class InternalServerException extends Exception {
	
	private String details;
	
	public InternalServerException(String str) {
		details = str;
	}
	
	public String toString() {
		return "InternalServerException[" + details + "]";
	}
}