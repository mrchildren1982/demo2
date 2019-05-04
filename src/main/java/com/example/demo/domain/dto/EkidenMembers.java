package com.example.demo.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EkidenMembers {

	@NotNull
	private Integer id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String sex;

}
