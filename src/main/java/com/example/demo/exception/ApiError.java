package com.example.demo.exception;

import java.util.List;

public class ApiError extends Exception {

	private String message;

	private List<String> messages;

	public void setMessage(String message) {

		this.message = message;

	}

	public String getMessage() {

		return this.message;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public List<String> getMessages() {
		return this.messages;
	}

	public void setDocumentationUrl(String string) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
