package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.service.DptService;
import com.example.demo.exception.BusinessException;

@RequestMapping("/dpt")
@RestController
public class DptController {

	@Autowired
	private DptService dptService;

	@PostMapping
	public ResponseEntity<?> insert() throws BusinessException {

		try {
			dptService.doExcecute();
		} catch (BusinessException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}


		return ResponseEntity.status(HttpStatus.CREATED).build();

	}
}
