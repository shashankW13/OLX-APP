package com.olx.exception;

public class InvalidCredentialsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidCredentialsException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid Client Credentials " + this.message;
	}
	
	
}
