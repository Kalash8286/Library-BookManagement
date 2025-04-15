package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookDTO;
import com.example.dto.BorrowBook;
import com.example.exception.BookNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.services.BookService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Validated
@RequestMapping("book")
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping("save")
	public ResponseEntity<BookDTO> saveBook(@Valid @RequestBody BookDTO bookDTO) {
		BookDTO book = bookService.saveBook(bookDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(book);
	}

	@GetMapping("get/all")
	public ResponseEntity<List<BookDTO>> getAllBook() throws BookNotFoundException {
		List<BookDTO> list = bookService.findAllBook();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("get/{id}")
	public ResponseEntity<BookDTO> getById(@PathVariable @Min(value = 1, message = "{book.id.positive}") int id)
			throws BookNotFoundException {
		BookDTO book = bookService.findByBookId(id);
		return ResponseEntity.status(HttpStatus.OK).body(book);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable @Min(value = 1, message = "{book.id.positive}") int id)
			throws BookNotFoundException {
		bookService.deleteBook(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PatchMapping("borrow/{bookId}")
	public ResponseEntity<Void> borrowBook(
			@PathVariable @Min(value = 1, message = "{book.id.positive}") int bookId,
			@Valid @RequestBody BorrowBook userEmail)
			throws BookNotFoundException, UserNotFoundException {

		bookService.borrowBook(bookId, userEmail.getEmail());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PatchMapping("return/{bookId}")
	public ResponseEntity<Void> returnBook(@PathVariable @Min(value = 1, message = "{book.id.positive}")  int bookId) throws BookNotFoundException {
		bookService.returnBook(bookId);
		return ResponseEntity.status(HttpStatus.OK).build();

	}

}
