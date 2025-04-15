package com.example.dto;

import jakarta.validation.constraints.NotBlank;

public class BookDTO {

	private int id;
	@NotBlank(message = "{book.title.required}")
	private String title;
	@NotBlank(message = "{book.author.required}")
	private String author;
	@NotBlank(message = "{book.description.required}")
	private String description;
	private boolean borrowed;
	private UserDTO borrowedBy;

	public BookDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public BookDTO(int id, @NotBlank(message = "{book.title.required}") String title,
			@NotBlank(message = "book.author.required") String author,
			@NotBlank(message = "book.description.required") String description, boolean isBorrowed,
			UserDTO borrowedBy) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.borrowed = isBorrowed;
		this.borrowedBy = borrowedBy;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean isBorrowed) {
		this.borrowed = isBorrowed;
	}

	public UserDTO getBorrowedBy() {
		return borrowedBy;
	}

	public void setBorrowedBy(UserDTO borrowedBy) {
		this.borrowedBy = borrowedBy;
	}

}
