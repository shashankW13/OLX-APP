package com.olx.exceptions;

public class UserNameDoesNotExistException extends Exception {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String message;

	
	public UserNameDoesNotExistException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public UserNameDoesNotExistException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "User Name Does Not Exist Exception =" + message;
	}
}
