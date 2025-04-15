package com.example.services;


import java.util.List;

import com.example.dto.BookDTO;
import com.example.exception.BookNotFoundException;
import com.example.exception.UserNotFoundException;

public interface BookService {

	BookDTO saveBook(BookDTO book);
	
	List<BookDTO> findAllBook() throws BookNotFoundException;
	
	BookDTO findByBookId(int id) throws BookNotFoundException;
	
	void deleteBook(int id)throws BookNotFoundException;
	
	void borrowBook(int bookId, String userEmail) throws BookNotFoundException, UserNotFoundException;
	
	void returnBook(int bookId)throws BookNotFoundException;
		
}

