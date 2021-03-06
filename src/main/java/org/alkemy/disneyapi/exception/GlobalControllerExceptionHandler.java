package org.alkemy.disneyapi.exception;


import java.time.ZonedDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice

public class GlobalControllerExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleCharacterException (CharacterNotFoundException exc){
	
	ErrorResponse error = new ErrorResponse();
	
	error.setStatus(HttpStatus.NOT_FOUND.value());
	error.setMessage(exc.getMessage());
	error.setTimeStamp(ZonedDateTime.now());
	
	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleMovieException (MovieNotFoundException exc){
	
	ErrorResponse error = new ErrorResponse();
	
	error.setStatus(HttpStatus.NOT_FOUND.value());
	error.setMessage(exc.getMessage());
	error.setTimeStamp(ZonedDateTime.now());
	
	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleUniqueUsernameException (DataIntegrityViolationException exc){

	ErrorResponse error = new ErrorResponse();
	
	Boolean duplicatedUser = exc.getMostSpecificCause().getMessage().contains("Duplicate entry");
	
	HttpStatus status;
	
	if(duplicatedUser) {
		error.setMessage("The username already exist!");
		error.setStatus(HttpStatus.CONFLICT.value());
		status = HttpStatus.CONFLICT;
	} else {
		error.setMessage(exc.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		status = HttpStatus.BAD_REQUEST;
	}
		
	
	error.setTimeStamp(ZonedDateTime.now());

	return new ResponseEntity<>(error, status);

	}
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException (Exception exc){

	ErrorResponse error = new ErrorResponse();
	
	error.setStatus(HttpStatus.BAD_REQUEST.value());
	error.setMessage(exc.getMessage());
	error.setTimeStamp(ZonedDateTime.now());

	return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

	
	
}
