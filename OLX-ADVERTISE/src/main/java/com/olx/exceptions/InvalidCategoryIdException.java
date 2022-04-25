package com.olx.exceptions;

public class InvalidCategoryIdException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	
	public InvalidCategoryIdException() {
		this.message = "";
		// TODO Auto-generated constructor stub
	}


	public InvalidCategoryIdException(String message) {
		this.message = message;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Invalid Category Id Exception " + message;
	}
	
	

}
