package com.example.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.dto.BookDTO;
import com.example.dto.UserDTO;
import com.example.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	BookService bookServiceMock;

	private BookDTO bookDTO;
	private UserDTO userDTO;

	@BeforeEach
	void setUp() {
		userDTO = new UserDTO(1, "kalash", "mumbai", "kalash@gmail.com");
		bookDTO = new BookDTO(100, "testTitle", "testAuthor","testDescription", false, userDTO);
	}

	@Test
	void saveBookTest() throws Exception {

		when(bookServiceMock.saveBook(Mockito.any(BookDTO.class))).thenReturn(bookDTO);

		String jsonob = objectMapper.writeValueAsString(bookDTO);
	

		mockMvc.perform(post("/book/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonob))
				.andExpect(status().isOk())
				.andExpect(content().json(jsonob))
				.andExpect(result -> System.out.println(result.getResponse().getContentAsString()));

	}

	@Test
	void getAllBookTest() throws Exception {

		when(bookServiceMock.findAllBook()).thenReturn(Arrays.asList(bookDTO));
		
		
		mockMvc.perform(get("/book/get/all"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(bookDTO))));
	}

}
