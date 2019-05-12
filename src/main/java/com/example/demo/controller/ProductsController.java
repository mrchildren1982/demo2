package com.example.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.Products;
import com.example.demo.domain.service.ProductsService;

@Valid
@RestController
@ResponseBody
public class ProductsController {

	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private ProductsService service;


	@GetMapping
	public Products getProducts() {

		return service.get();


	}


}
