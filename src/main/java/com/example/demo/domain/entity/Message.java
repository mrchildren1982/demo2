package com.example.demo.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="message")
@Data
public class Message {

	@Id
	private String text;

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
