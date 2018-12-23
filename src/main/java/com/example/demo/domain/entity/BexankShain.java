package com.example.demo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="shain")
@Data
public class BexankShain {

	/** 社員ID */
	@Id
	@Column(name = "id")
	private Long id;
	/** 社員名  */
	@Column(name = "name")
	private String name;
	/** 経験年数 */
	@Column(name="exp")
	private Long experience;

	public BexankShain() {

	}

	public BexankShain(Long id, String name, Long experience) {
		this.id = id;
		this.name =name;
		this.experience = experience;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getExperience() {
		return experience;
	}
	public void setExperience(Long experience) {
		this.experience = experience;
	}



}
