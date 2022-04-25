package com.olx.exception;

public class InvalidAuthTokenException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidAuthTokenException(String message) {
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
		return "Invalid Authentication Token " + this.message;
	}
	
	
}
