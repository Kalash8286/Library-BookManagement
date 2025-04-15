package com.example.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.dto.BookDTO;
import com.example.exception.BookNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.models.Book;
import com.example.models.User;
import com.example.repositories.BookRepository;
import com.example.repositories.UserRepository;
import com.example.services.BookService;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	@Value("${book.not.found}")
	private String bookNotFound;
	@Value("${user.not.found}")
	private String userNotFound;
	@Value("${books.not.available}")
	private String bookNotAvailable;
	@Value("${book.already.borrowed}")
	private String bookAlreadyBorrowed;

	public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository, ModelMapper modelMapper) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public BookDTO saveBook(BookDTO bookDTO) {
		Book book = modelMapper.map(bookDTO, Book.class);
		return modelMapper.map(bookRepository.save(book), BookDTO.class);
	}

	@Override
	public List<BookDTO> findAllBook() throws BookNotFoundException {
		List<Book> bookList = bookRepository.findAll();
		if (bookList.size() > 0) {
			List<BookDTO> bookDTOList = bookList.stream().map(book -> modelMapper.map(book, BookDTO.class))
					.collect(Collectors.toList());

			return bookDTOList;
		} else {
			throw new BookNotFoundException(bookNotAvailable);
		}

	}

	@Override
	public BookDTO findByBookId(int id) throws BookNotFoundException {

		Optional<Book> book = bookRepository.findById(id);

		if (book.isPresent()) {
			return modelMapper.map(book.get(), BookDTO.class);
		} else {
			throw new BookNotFoundException(String.format(bookNotFound, id));
		}
	}
	
	
	

	@Override
	public void deleteBook(int id) throws BookNotFoundException {

		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			bookRepository.deleteById(id);
		} else {
			throw new BookNotFoundException(String.format(bookNotFound, id));
		}

	}

	@Override
	public void borrowBook(int bookId, String userEmail) throws BookNotFoundException, UserNotFoundException {

		System.out.println(userEmail);
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException(String.format(bookNotFound, bookId)));
		User user = userRepository.findByEmail(userEmail)
				.orElseThrow(() -> new UserNotFoundException(String.format(userNotFound, userEmail)));

		if (!book.isBorrowed()) {
			book.setBorrowedBy(user);
			book.setBorrowed(true);
			bookRepository.save(book);
		} else {
			throw new BookNotFoundException(bookAlreadyBorrowed);
		}

	}

	@Override
	public void returnBook(int bookId) throws BookNotFoundException {

		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException(String.format(bookNotFound, bookId)));

		book.setBorrowed(false);
		book.setBorrowedBy(null);
		bookRepository.save(book);

	}

}