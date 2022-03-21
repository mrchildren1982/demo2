package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dao.Hoge;
import com.example.demo.domain.dao.HogeQuery;
import com.example.demo.validator.HogeValidator;

@RestController
@RequestMapping("/hoge")
public class HogeController {

	@Autowired
	private HogeValidator validator;

	@InitBinder
	public void InitBinder(WebDataBinder binder) {

		binder.addValidators(validator);
	}


	@GetMapping
	public Hoge get(@ModelAttribute HogeQuery query ) {

		return Hoge.builder().id(Long.valueOf(10L))
				.name("牧野").build();
	}
}
