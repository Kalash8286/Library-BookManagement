package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDTO;
import com.example.services.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("user")
public class UserController {


	@Autowired
	UserService userService;
	
	@PostMapping("save")
	public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO)
	{
		UserDTO user = userService.saveUser(userDTO);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	

	@GetMapping("get/all")
	public ResponseEntity<List<UserDTO>> getAllusers(){
		List<UserDTO> allUser = userService.getAllUser();
		return ResponseEntity.status(HttpStatus.OK).body(allUser);
	}
	
}
