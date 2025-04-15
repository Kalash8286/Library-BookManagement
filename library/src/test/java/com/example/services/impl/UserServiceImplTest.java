package com.example.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.example.dto.UserDTO;
import com.example.models.User;
import com.example.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	
	@Mock
	private UserRepository userRepositoryMock;
	
	@Mock
	private ModelMapper modelMapperMock;
	
	@Spy
	@InjectMocks
	private UserServiceImpl userServiceImplMock;
	
	private UserDTO userDTO;
	private User user;
	
	@BeforeEach
	void setUp() {
		
		userDTO = new UserDTO(101, "Kalash", "Mumbai", "kalash@gmail.com");
		user = new User(101, "Kalash", "Mumbai", "kalash@gmail.com");
		
	}
	
	
	@Test
	void saveUser_WhenUserDTOisGiven_ReturnUserDTO() {
		
		when(modelMapperMock.map(userDTO, User.class)).thenReturn(user);
		when(userRepositoryMock.save(user)).thenReturn(user);
		when(modelMapperMock.map(user, UserDTO.class)).thenReturn(userDTO);
		
		UserDTO actual_UserDTO = userServiceImplMock.saveUser(userDTO);
		
		assertEquals(userDTO, actual_UserDTO);
		verify(userServiceImplMock).saveUser(userDTO);
	}	
	
	
	
	
	
}