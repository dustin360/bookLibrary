package com.booklibrary.demo.exception;

/**
 * Created by David on 01 Oct, 2020
 **/
public class BadRequestException extends RuntimeException {

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException() {
		super("bad request");
	}

}
