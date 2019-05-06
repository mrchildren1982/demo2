package com.example.demo.domain.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EkidenDto {

	@Valid
	@NotNull
	private List<EkidenMembers> members;

	@Valid
	@NotNull
	private List<EkidenOrder> orders;

}
