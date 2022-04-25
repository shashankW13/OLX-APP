package com.olx.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.SignatureException;

@ControllerAdvice
public class GlobalExeptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = InvalidCredentialsException.class)
	public ResponseEntity<Object> handleNullEntity(RuntimeException exception, WebRequest request){
		String errorMessage = "{\"error\":\"" + exception.toString() + "\"}";
		return handleExceptionInternal(exception, errorMessage, new HttpHeaders(), 
						HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = InvalidAuthTokenException.class)
	public ResponseEntity<Object> handleInvalidEntity(RuntimeException exception, WebRequest request){
		String errorMessage = "{\"error\":\"" + exception.toString() + "\"}";
		return handleExceptionInternal(exception, errorMessage, new HttpHeaders(), 
						HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = SignatureException.class)
	public ResponseEntity<Object> handleInvalidToken(RuntimeException exception, WebRequest request){
		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), 
						HttpStatus.BAD_REQUEST, request);
	}
}
