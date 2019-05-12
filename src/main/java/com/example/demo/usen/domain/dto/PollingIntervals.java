package com.example.demo.usen.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class PollingIntervals extends LargeFileStreamingReponseBody{


	private List<PollingInterval> pollingIntervals;

}
