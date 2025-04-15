package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {

	private int id;
	@NotBlank(message = "{user.name.required}")
	private String name;
	@NotBlank(message = "{user.city.required}")
	private String city;
	@Email(message = "{user.email.valid}")
	private String email;

	


	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDTO(int id, String name, String city, @Email String email) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", city=" + city + ", email=" + email + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
