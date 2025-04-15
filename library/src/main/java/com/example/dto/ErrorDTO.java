package com.example.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorDTO {

	private String message;
	private HttpStatus status;
	private int statusCode;
	private LocalDateTime timestamp;
	
	
	
	public ErrorDTO(String message, HttpStatus status, int statusCode, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.status = status;
		this.statusCode = statusCode;
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
	
	
}
