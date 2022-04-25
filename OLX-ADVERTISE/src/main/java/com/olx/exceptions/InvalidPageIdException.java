package com.olx.exceptions;

public class InvalidPageIdException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	
	public InvalidPageIdException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public InvalidPageIdException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Invalid Page Id Exception = " + message;
	} 
	
	

}
