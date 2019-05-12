package com.example.demo.hoge.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollingIntervalDto {

	private PollingInterval pollingInterval;

	private DefaultPollingInterval defaultPollingInterval;

}
