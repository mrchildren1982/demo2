package com.example.demo.usen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultPollingInterval {

	private Integer id;

	private Integer pollingInterval;

	private String remarks;
}
