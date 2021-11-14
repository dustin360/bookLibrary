package com.booklibrary.demo.exception;

/** Created By David on 15-May-2020 */
public class ForbiddenException extends RuntimeException {

	public ForbiddenException(String message) {
		super(message);
	}

	public ForbiddenException() {
		super("Access forbidden");
	}

}
