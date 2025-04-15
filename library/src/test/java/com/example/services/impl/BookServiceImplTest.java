package com.example.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.dto.BookDTO;
import com.example.dto.UserDTO;
import com.example.exception.BookNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.models.Book;
import com.example.models.User;
import com.example.repositories.BookRepository;
import com.example.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

	@Mock
	private BookRepository bookRepositoryMock;
	@Mock
	private UserRepository userRepositoryMock;
	@Mock
	private ModelMapper modelMapperMock;
	

	@InjectMocks
	private BookServiceImpl bookServiceImplMock;

	private BookDTO bookDTO;
	private UserDTO userDTO;
	private Book book;
	private User user;

	@BeforeEach
	void setUp() {

		userDTO = new UserDTO(1, "testUser", "testCity", "test@email.com");
		bookDTO = new BookDTO(100, "testTitle", "testAuthor", "testDescription", false, userDTO);

		user = new User(1, "testUser", "testCity", "test@email.com");
		book = new Book(100, "testTitle", "testAuthor", "testDescription", false, user);
		
		ReflectionTestUtils.setField(bookServiceImplMock, "bookNotFound", "Book not found with id %s");;
		ReflectionTestUtils.setField(bookServiceImplMock, "userNotFound", "User not found with id %s");;
		

	}

	@Test
	@DisplayName("When BookDTO is given to save return saved BookDTO object")
	void save_WhenBookDTOisGiven_ReturnBookDTO() {

//		Arrange
		when(modelMapperMock.map(bookDTO, Book.class)).thenReturn(book);
		when(modelMapperMock.map(book, BookDTO.class)).thenReturn(bookDTO);
		when(bookRepositoryMock.save(book)).thenReturn(book);

//		Act
		BookDTO Expected_bookDTO = bookServiceImplMock.saveBook(bookDTO);

//		Assert
		assertEquals(Expected_bookDTO, bookDTO);
	}

//	=======================================
//	=========================================

	@Test
	void findAllBook_ReturnAllBooks() throws BookNotFoundException {
//		Arrange
		List<Book> bookList = new ArrayList<>(Arrays.asList(book));
		List<BookDTO> bookDTOList = new ArrayList<>(Arrays.asList(bookDTO));
		when(bookRepositoryMock.findAll()).thenReturn(bookList);
		when(modelMapperMock.map(book, BookDTO.class)).thenReturn(bookDTO);

//		Act
		List<BookDTO> expected_bookDTOList = bookServiceImplMock.findAllBook();
//		Assert
		assertEquals(expected_bookDTOList, bookDTOList);
	}

	@Test
	void findAllBook_WhenNoBooksAvailble_ThrowException() throws BookNotFoundException {
//		Arrange
		List<Book> bookList = new ArrayList<>();
		when(bookRepositoryMock.findAll()).thenReturn(bookList);

//		Act & Assert
		Exception ex = assertThrows(BookNotFoundException.class, () -> bookServiceImplMock.findAllBook());

	}

//	=======================================
//	=========================================
	@Test
	void findByIdBook_WhenBookIdisGiven_ReturnBookDTO() throws BookNotFoundException {
//		Arrange
		when(bookRepositoryMock.findById(book.getId())).thenReturn(Optional.of(book));
		when(modelMapperMock.map(book, BookDTO.class)).thenReturn(bookDTO);
//		ACt
		BookDTO expected_BookDTO = bookServiceImplMock.findByBookId(book.getId());
//		Assert
		assertEquals(expected_BookDTO, bookDTO);
	}

	@Test
	void findByIdBook_WhenBookIdisNotPresent_throwException() throws BookNotFoundException {
//		Arrange
		when(bookRepositoryMock.findById(book.getId())).thenReturn(Optional.empty());
//		Act &	Assert
		assertThrows(BookNotFoundException.class, () -> bookServiceImplMock.findByBookId(book.getId()));
					 
	}

	
//	=======================================
//	=========================================
	@Test
	void deleteBook_WhenBookIdisPresent_ReturnVoid() throws BookNotFoundException {
//		Arrange
		when(bookRepositoryMock.findById(book.getId())).thenReturn(Optional.of(book));
		doNothing().when(bookRepositoryMock).deleteById(book.getId());
//		Act
		bookServiceImplMock.deleteBook(book.getId());
//		Assert
		verify(bookRepositoryMock).deleteById(book.getId());
	}

	@Test
	void deleteBook_WhenBookIDisNotPresent_ThrowException() {
//		Arrange
		when(bookRepositoryMock.findById(book.getId())).thenReturn(Optional.empty());
//	Act & Assert
		Exception ex = assertThrows(BookNotFoundException.class, () -> bookServiceImplMock.deleteBook(book.getId()));

//		
	}

//	=======================================
//	=========================================
	@Test
	void borrowBook_WhenParamIsGIven_UpdateRecord() throws BookNotFoundException, UserNotFoundException {
//		Arrange
		when(bookRepositoryMock.findById(book.getId())).thenReturn(Optional.of(book));
		when(userRepositoryMock.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
		when(bookRepositoryMock.save(book)).thenReturn(book);
//		Act
		bookServiceImplMock.borrowBook(book.getId(), user.getEmail());
//		Assert
		verify(bookRepositoryMock).save(book);

	}

	@Test
	void borrowBook_WhenUserIsNotPresent_ThrowException() {
//		Arrange
		when(bookRepositoryMock.findById(book.getId())).thenReturn(Optional.of(book));
		when(userRepositoryMock.findByEmail(user.getEmail())).thenReturn(Optional.empty());
//		Act &	Assert
		Exception ex = assertThrows(UserNotFoundException.class,
				() -> bookServiceImplMock.borrowBook(book.getId(), user.getEmail()));


	}

	@Test
	void borrowBook_WhenBookIsNotPresent_ThrowException() {
//		Arrange
		when(bookRepositoryMock.findById(book.getId())).thenReturn(Optional.empty());
//		Act &	Assert
		Exception ex = assertThrows(BookNotFoundException.class,
				() -> bookServiceImplMock.borrowBook(book.getId(), user.getEmail()));



	}

	@Test
	void borrowBook_WhenBookIsAlreadyBorrowed_ThrowException() {
//		Arrange
		book.setBorrowed(true);
		when(bookRepositoryMock.findById(book.getId())).thenReturn(Optional.of(book));
		when(userRepositoryMock.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
//		Act &	Assert
		Exception ex = assertThrows(BookNotFoundException.class,
				() -> bookServiceImplMock.borrowBook(book.getId(), user.getEmail()));



	}

//	=======================================
//	=========================================
	@Test
	void returnBook_WhenBookIdIsValid_UpdateBook() throws BookNotFoundException {

//		Arrange
		when(bookRepositoryMock.findById(book.getId())).thenReturn(Optional.of(book));
		when(bookRepositoryMock.save(book)).thenReturn(book);
//		Act		
		bookServiceImplMock.returnBook(book.getId());
//		Assert
		verify(bookRepositoryMock).save(book);
	}

	@Test
	void returnBook_WhenBookIdIsNotPresent_ThrowException() throws BookNotFoundException {

//		Arrange
		when(bookRepositoryMock.findById(book.getId())).thenReturn(Optional.empty());
//		Act & Assert
		Exception ex = assertThrows(BookNotFoundException.class, () -> bookServiceImplMock.returnBook(book.getId()));
	}

}
