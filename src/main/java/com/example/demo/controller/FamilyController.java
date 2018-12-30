package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.FamilyDto;
import com.example.demo.domain.service.FamilyService;
import com.example.demo.exception.DataNotFoundException;

@RestController
@RequestMapping("/family")
public class FamilyController {

	@Autowired
	private MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(FamilyController.class);
	@Autowired
	private FamilyService familyService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FamilyDto>> getFamilyInfo() throws Exception {

		List<FamilyDto> familys = familyService.getFamilyData();
		return ResponseEntity.ok(familys);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<?> handleException(DataNotFoundException ex) {

		logger.warn("handlerExcption", ex);

		Map<String, Object> body = new HashMap<>();
		body.put("error_code", "100");
		body.put("error_message", messageSource.getMessage("data_not_found", null, Locale.JAPAN));

		HttpHeaders headers = new HttpHeaders();
		headers.add("header", "hoge");

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(body, headers, httpStatus);
	}
}
