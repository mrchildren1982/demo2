package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.FamilyDto;
import com.example.demo.domain.service.FamilyService;

@RestController
@RequestMapping("/family")
public class FamilyController {

	@Autowired
	private FamilyService familyService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FamilyDto>> getFamilyInfo() {

		List<FamilyDto> familys =familyService.getFamilyData();
		return ResponseEntity.ok(familys);
	}

}
