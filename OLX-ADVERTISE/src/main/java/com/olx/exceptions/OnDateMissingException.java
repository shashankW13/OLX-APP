package com.olx.exceptions;

public class OnDateMissingException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	
	public OnDateMissingException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public OnDateMissingException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "No On date exists =" + message;
	}
	
	

}
