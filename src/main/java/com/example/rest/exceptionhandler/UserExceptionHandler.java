package com.example.rest.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.rest.exception.UserNotFoundByIdException;
import com.example.rest.utility.CustomResponseBuilder;
import com.example.rest.utility.ErrorStructure;

@RestControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(UserNotFoundByIdException.class)
	private ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(UserNotFoundByIdException ex){
		return CustomResponseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage()," user not found by given ID");
	}
}
