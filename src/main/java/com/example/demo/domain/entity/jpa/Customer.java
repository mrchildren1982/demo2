package com.example.demo.domain.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

	@Column(name = "id")
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	@Email
	private String email;

	@Override
	public String toString() {

		return "id: " + id + " name: " + name +" email: " + email;
	}
}
