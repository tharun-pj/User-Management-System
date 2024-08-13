package com.example.rest.utility;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class CustomResponseBuilder {

	public static<T> ResponseEntity<ErrorStructure<T>> error(HttpStatus status,String message,T rootCause){

		return ResponseEntity
				.status(status)
				.body(ErrorStructure.create(status.value(), message, rootCause));
	}

	public static <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status ,String message ,T data){
		return ResponseEntity
				.status(status)
				.body(ResponseStructure.create(status.value(),message, data));
	}

	public ResponseEntity<Object> fieldErrors(HttpStatus status,String message,List<CustomFieldError> rootcause){
		return ResponseEntity.status(status).body(ErrorStructure.create(status.value(), message, rootcause));	
	}
}
