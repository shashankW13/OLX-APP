package com.olx.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<Object> handleConflictForCredentials(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \"Invalid Credentials Exception\"}";
		ResponseEntity<Object> response = 	
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		System.out.println(request);
		return response;
	}
	
	
	@ExceptionHandler(value = InvalidAuthTokenException.class)
	public ResponseEntity<Object> handleConflictForAuthToken(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \"Invalid Authentication token\"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		System.out.println(request);
		return response;
	}
	
	
	@ExceptionHandler(value = InvalidCategoryIdException.class)
	public ResponseEntity<Object> handleConflictForCategoryId(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \"Invalid Catrgory Id\"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		System.out.println(request);
		return response;
	}
	
	
	@ExceptionHandler(value = InvalidPageIdException.class)
	public ResponseEntity<Object> handleConflictForPageId(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \"InvalidCredentialsException\"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		System.out.println(request);
		return response;
	} 
	
	@ExceptionHandler(value = InvalidStatusIdException.class)
	public ResponseEntity<Object> handleConflictForStatusId(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \"InvalidCredentialsException\"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		System.out.println(request);
		return response;
	}
	
	
	@ExceptionHandler(value = OnDateMissingException.class)
	public ResponseEntity<Object> handleConflictForOnDate(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \"InvalidCredentialsException\"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		System.out.println(request);
		return response;
	} 
	
	
	@ExceptionHandler(value = ToDateMissingException.class)
	public ResponseEntity<Object> handleConflictForToDate(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \"InvalidCredentialsException\"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		System.out.println(request);
		return response;
	}
	
	
	@ExceptionHandler(value = FromDateMissingException.class)
	public ResponseEntity<Object> handleConflictForFromDate(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \"InvalidCredentialsException\"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		System.out.println(request);
		return response;
	} 
	
	@ExceptionHandler(value = UserNameDoesNotExistException.class)
	public ResponseEntity<Object> handleConflictForUserNameDoesNotExist(RuntimeException exception, WebRequest request) {
		String errorMessage = "{\"error\" : \"InvalidCredentialsException\"}";
		ResponseEntity<Object> response = 
				
				handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
		System.out.println(request);
		return response;
	}
	
}

