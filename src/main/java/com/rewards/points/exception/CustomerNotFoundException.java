package com.rewards.points.exception;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8100869918457006514L;

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerNotFoundException(String message) {
		super(message);
	}

	public CustomerNotFoundException() {

	}

}
