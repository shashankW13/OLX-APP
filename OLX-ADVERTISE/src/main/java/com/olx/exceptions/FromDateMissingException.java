package com.olx.exceptions;

public class FromDateMissingException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	
	public FromDateMissingException() {
		this.message = "";
	}


	public FromDateMissingException(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "Invalid Credentials Exception = " + message;
	}
	
	

}
