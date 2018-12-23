package com.example.demo.controller;

import java.util.List;

import com.example.demo.domain.entity.SampleEntity;
import com.example.demo.domain.service.SampleService;

//@RestController
public class SampleController {

//	@Autowired
	SampleService sampleService;

//	@RequestMapping(method = RequestMethod.GET, value="/sample")
	public List<SampleEntity> getSample() {
		return sampleService.getSample();
	}
}
