package com.example.demo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBelonging() {
		return belonging;
	}

	public void setBelonging(String belonging) {
		this.belonging = belonging;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}



}
