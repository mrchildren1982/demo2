package com.example.demo.domain.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "history")
public class History {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name="name")
	private String name;
}
