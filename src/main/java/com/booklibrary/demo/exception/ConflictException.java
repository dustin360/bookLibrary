package com.booklibrary.demo.exception;

/**
 * Created by David on 09 Sep, 2020
 **/
public class ConflictException extends RuntimeException {

	public ConflictException(String message) {
		super(message);
	}

	public ConflictException() {
		super("a conflict was found");
	}

}
