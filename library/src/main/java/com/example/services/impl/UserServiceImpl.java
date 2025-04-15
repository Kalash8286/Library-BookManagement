package com.example.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.dto.UserDTO;
import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDTO saveUser(UserDTO userDTO) {

		User user = modelMapper.map(userDTO, User.class);
		return modelMapper.map(userRepository.save(user), UserDTO.class);

	}

	@Override
	public List<UserDTO> getAllUser() {
		List<UserDTO> list = userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		return list;
	}


}