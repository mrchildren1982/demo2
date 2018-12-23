package com.example.demo.domain.dto;


import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AttachmentDto {

	@NotBlank
	private Integer id;

	private String belonging;

	private String work;

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
	 * @return belonging
	 */
	public String getBelonging() {
		return belonging;
	}

	/**
	 * @param belonging セットする belonging
	 */
	public void setBelonging(String belonging) {
		this.belonging = belonging;
	}

	/**
	 * @return work
	 */
	public String getWork() {
		return work;
	}

	/**
	 * @param work セットする work
	 */
	public void setWork(String work) {
		this.work = work;
	}




}
