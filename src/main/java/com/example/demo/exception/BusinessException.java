package com.example.demo.exception;

public class BusinessException extends Exception{

	private String[] messages;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String... messages) {

	}

	public void setMessage(String... messages) {

		this.messages = messages;
	}

	public String[] getMessages() {
		return this.messages;
	}
}
