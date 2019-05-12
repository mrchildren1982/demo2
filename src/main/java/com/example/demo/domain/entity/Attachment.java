package com.example.demo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attachment")
public class Attachment {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "belonging")
	private String belonging;

	@Column(name = "work")
	private String work;


}
