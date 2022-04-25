package com.olx.exceptions;

public class InvalidCredentialsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	
	public InvalidCredentialsException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public InvalidCredentialsException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Invalid Credentials Exception =" + message;
	}
	
	

}
