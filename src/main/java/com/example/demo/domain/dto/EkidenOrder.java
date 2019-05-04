package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EkidenOrder {

	private Integer id;

	private Integer kukan;

	private Integer membersId;

	private String sankaFlag;


}
