package com.booklibrary.demo.config;

import com.booklibrary.demo.dto.GeneralResponse;
import com.booklibrary.demo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(value = { NotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public GeneralResponse misMatchErrorHandler(NotFoundException ex) {
		System.out.println("throwing this::::::::::::: " + ex.getMessage());
		return new GeneralResponse(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { ForbiddenException.class })
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public GeneralResponse forbiddenErrorHandler(ForbiddenException ex) {
		return new GeneralResponse(HttpStatus.UNAUTHORIZED.value(),
				ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public GeneralResponse handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex) {
		System.out.println("Method Argument not valid throwing....");

		List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getDefaultMessage())
				.collect(Collectors.toList());

		return new GeneralResponse(HttpStatus.BAD_REQUEST.value(), errorList.get(0));

	}

	@ExceptionHandler(value = { EntityAlreadyExistsException.class })
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public GeneralResponse entityAlreadyExistsErrorHandler(
			EntityAlreadyExistsException ex) {
		return new GeneralResponse(HttpStatus.CONFLICT.value(), ex.getLocalizedMessage());
	}


	@ExceptionHandler(value = { IllegalArgumentException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public GeneralResponse illegalArgumentExceptionHandler(IllegalArgumentException ex) {
		return new GeneralResponse(HttpStatus.BAD_REQUEST.value(),
				ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { ConflictException.class })
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public GeneralResponse conflictExceptionHandler(ConflictException ex) {
		return new GeneralResponse(HttpStatus.CONFLICT.value(), ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { BadRequestException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public GeneralResponse badRequestExceptionHandler(BadRequestException ex) {
		return new GeneralResponse(HttpStatus.BAD_REQUEST.value(),
				ex.getLocalizedMessage());
	}

}
