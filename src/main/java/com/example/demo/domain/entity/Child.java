package com.example.demo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_child")
public class Child {

	@Id
	@Column(name = "id")
	private Integer id;

	private Integer familyId;

	private String name;

	private Integer age;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return familyId
	 */
	public Integer getFamilyId() {
		return familyId;
	}

	/**
	 * @param familyId セットする familyId
	 */
	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age セットする age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}


}
