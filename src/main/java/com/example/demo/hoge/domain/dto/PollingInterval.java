package com.example.demo.hoge.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollingInterval  {

	private Integer id;

	private Integer  stbAccount;

	private String stbSerial;

	private Integer custumInterval;

	private String remarks;


}
