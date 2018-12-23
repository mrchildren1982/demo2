package com.example.demo.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "shain")
public class Shain {

	@Id
	private Long id;

	private String name;

	private Long exp;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setExp(Long exp) {
		this.exp = exp;

	}

	public Long getExp() {
		return this.exp;
	}
}
