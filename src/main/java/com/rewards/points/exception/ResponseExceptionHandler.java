package com.rewards.points.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rewards.points.model.ErrorResponse;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { InvalidInputException.class })
	public final ResponseEntity<ErrorResponse> handleInvalidInputException(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(400, "INVALID_INPUT", "Invalid input");
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { CustomerNotFoundException.class })
	public final ResponseEntity<ErrorResponse> handleCustomerNotFoundException(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(404, "CUSTOMER_NOT_FOUND", "Customer not found");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { RuntimeException.class })
	public final ResponseEntity<ErrorResponse> handleRuntimeException(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(500, "INTERNAL_SERVER_ERROR", "Something went wrong");
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(400, "BAD_REQUEST", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
