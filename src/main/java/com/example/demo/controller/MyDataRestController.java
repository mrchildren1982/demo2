package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.MyData;
import com.example.demo.domain.service.MyDataService;

@RestController
@RequestMapping("/rest")
public class MyDataRestController {

	@Autowired
	private MyDataService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<MyData> restAll() {
		return service.getAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{num}")
	public MyData restBy(@PathVariable int num) {
		return service.get(num);
	}
}
