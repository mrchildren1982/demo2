package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.SampleEntity;
import com.example.demo.domain.service.SampleService;

@RestController
public class SampleController {

	@Autowired
	SampleService sampleService;

	@RequestMapping(method = RequestMethod.GET, value="/sample")
	public List<SampleEntity> getSample() {
		return sampleService.getSample();
	}
}
