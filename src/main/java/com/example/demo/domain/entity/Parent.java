package com.example.demo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_parent")
public class Parent {

	@Id
	@Column(name = "id")
	private Integer id;

	private Integer familyId;

	private boolean isHusband;

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
	 * @return isHusband
	 */
	public boolean isHusband() {
		return isHusband;
	}

	/**
	 * @param isHusband セットする isHusband
	 */
	public void setHusband(boolean isHusband) {
		this.isHusband = isHusband;
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
