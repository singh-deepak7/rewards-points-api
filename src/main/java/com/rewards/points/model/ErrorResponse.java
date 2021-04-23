package com.rewards.points.model;

import java.io.Serializable;

public class ErrorResponse implements Serializable{

	private static final long serialVersionUID = 2926875557954723776L;
	
	private int status;
	private String error;
	private String message;
	
	public ErrorResponse(int status, String error, String message) {
		this.status = status;
		this.error = error;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorResponse [status=" + status + ", error=" + error + ", message=" + message + "]";
	}
	

}
