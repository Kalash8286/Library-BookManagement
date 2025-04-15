package com.example.services;

import java.util.List;

import com.example.dto.UserDTO;

public interface UserService {

	public UserDTO saveUser(UserDTO userDTO);
	public List<UserDTO> getAllUser();
	
}
