package com.magneto.mutants.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorMessage {

	private HttpStatus status;
	private String message;
	
	public ErrorMessage(HttpStatus status, Throwable ex) {
        this.status = status;
        this.message = ex.getMessage();
    }
	
	public ErrorMessage() {}
	
	/**
	 * @return
	 */
	public HttpStatus getStatus() {
		return status;
	}
	
	/**
	 * @param status
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
