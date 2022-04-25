package com.olx.exceptions;

public class ToDateMissingException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	
	public ToDateMissingException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public ToDateMissingException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "No To Todate =" + message;
	}
	
	

}
