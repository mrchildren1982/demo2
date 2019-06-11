package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.AttatchmentDoma;
import com.example.demo.domain.service.BatchService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/batch")
public class BatchController {

	@Autowired
	private BatchService batchService;

	@PostMapping
	public int[] batchInsert(@RequestBody List<AttatchmentDoma> attachments) {

		return batchService.batchInsert(attachments);

	}

	@DeleteMapping("/{id}")
	public void batchDelete(@RequestParam @JsonFormat(pattern = "yyyyMMdd") String to,@RequestParam int batchSize) {

		int year = parseToInt(to.substring(0, 4));
		int month = parseToInt(to.substring(4, 6));
		int date = parseToInt(to.substring(6, 8));

		batchService.batchDelete(LocalDateTime.of(year, month, date, 0, 0), batchSize);
	}



	@GetMapping("/paging")
	public List<AttatchmentDoma> selectByDate(@RequestParam @JsonFormat(pattern = "yyyyMMdd") String to,
			@RequestParam Integer limit, @RequestParam Integer offset) {

		int year = parseToInt(to.substring(0, 4));
		int month = parseToInt(to.substring(4, 6));
		int date = parseToInt(to.substring(6, 8));
		return batchService.selectByDate(LocalDateTime.of(year, month, date, 0, 0), limit ,offset);
	}

	public static int parseToInt(String target) {

		return Integer.parseInt(target);
	}

}
