package com.example.demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Shain;
import com.example.demo.domain.service.ShainService;
import com.example.demo.exception.DataNotFoundException;

@RestController
@RequestMapping("/shain")
public class ShainController {

	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(ShainController.class);

	@Autowired
	private ShainService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<Shain> getShains() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Shain getShain(@PathVariable("id") Long id) {
		return service.find(id);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Shain updateShain(@PathVariable Long id, @RequestBody Shain shain) {

		shain.setId(id);
		return service.update(shain);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Shain insertShain(@Validated @RequestBody Shain shain) {
		return service.insertShain(shain);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable Long id) {

		service.delete(id);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<?> handleException(DataNotFoundException ex) {

		logger.warn("handlerException", ex);

		Map<String, Object> body = Collections.singletonMap("message", "invalid");

		HttpHeaders headers = new HttpHeaders();
		headers.add("example", "zzz");

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(body, headers, httpStatus);
	}
}
