package com.example.demo.domain.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;

@Entity
@Data
@Table(name="attachment")
public class AttatchmentDoma {


	@Id
	@Column(name = "id")
	@NotNull
	private Integer id;

	@Column(name = "belonging")
	@NotEmpty
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
