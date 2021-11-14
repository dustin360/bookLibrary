package com.booklibrary.demo.exception;

/** Created By David on 15-May-2020 */
public class NotFoundException extends RuntimeException {

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException() {
		super("not found");
	}

}
