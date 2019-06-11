package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	private String lastName;

	private String firstName;

	@Override
	public String toString() {
		return "firstName: " + firstName + " ,lastName: " + lastName;
 	}
}
