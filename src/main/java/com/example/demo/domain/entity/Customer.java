package com.example.demo.domain.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

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
