package com.example.dto;

import jakarta.validation.constraints.Email;

public class BorrowBook {

	@Email(message = "{user.email.valid}")
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
}
