package com.rewards.points.exception;

public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 8100869918457006514L;

	public InvalidInputException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidInputException(String message) {
		super(message);
	}

	public InvalidInputException() {

	}

}
