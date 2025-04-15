package com.example.controller.advice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.dto.ErrorDTO;
import com.example.exception.BookNotFoundException;
import com.example.exception.UserNotFoundException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleBookNotFoundException(BookNotFoundException exception) {
		ErrorDTO errorDTO = new ErrorDTO(exception.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
				LocalDateTime.now());

		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);

	}

	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException exception) {
		ErrorDTO errorDTO = new ErrorDTO(exception.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
				LocalDateTime.now());

		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);

	}

	@ExceptionHandler
	public ResponseEntity<List<ErrorDTO>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException exception) {

		List<ErrorDTO> errorDTOList = exception.getBindingResult().getAllErrors().stream()
				.map(error -> new ErrorDTO(error.getDefaultMessage(), HttpStatus.BAD_REQUEST,
						HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()))
				.toList();
		return ResponseEntity.status(errorDTOList.get(0).getStatus()).body(errorDTOList);

	}
	
	@ExceptionHandler
	public ResponseEntity<List<ErrorDTO>> handleConstraintViolationException(
			ConstraintViolationException exception) {

		List<ErrorDTO> errorDTOList = exception.getConstraintViolations().stream()
				.map(error -> new ErrorDTO(error.getMessage(), HttpStatus.BAD_REQUEST,
						HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()))
				.toList();
		return ResponseEntity.status(errorDTOList.get(0).getStatus()).body(errorDTOList);

	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleGenericException(Exception exception) {
		ErrorDTO errorDTO = new ErrorDTO(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
	}

}
