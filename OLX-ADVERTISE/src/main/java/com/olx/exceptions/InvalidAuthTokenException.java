package com.olx.exceptions;

public class InvalidAuthTokenException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	
	public InvalidAuthTokenException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public InvalidAuthTokenException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Invalid Authentication Token =" + message;
	}
	
	

}
