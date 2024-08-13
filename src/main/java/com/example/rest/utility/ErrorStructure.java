package com.example.rest.utility;

import org.springframework.http.ResponseEntity;

public class ErrorStructure<T> {

	private int status;
	private String message;
	private T rotCause;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getRotCause() {
		return rotCause;
	}
	public void setRotCause(T rotCause) {
		this.rotCause = rotCause;
	}
	
	public  static <T> ErrorStructure<T> create(int status,String message,T rootCause) {
		ErrorStructure<T> error = new ErrorStructure();
		error.setStatus(status);
		error.setMessage(message);
		error.setRotCause(rootCause);
		
		return error;
	}
}
