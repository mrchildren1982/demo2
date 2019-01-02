package com.example.demo.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.dao.MyDataDaoImpl;
import com.example.demo.domain.dto.MyData;
import com.example.demo.domain.repository.MyDataRepository;

@Controller
@RequestMapping("/mydata")
public class MyDataController {

	@Autowired
	MyDataRepository repository;


	@Autowired
	MyDataDaoImpl dao;

	@PostConstruct
	public void init() {

		MyData d1 = new MyData();
		d1.setName("tuyano");
		d1.setAge(123);
		d1.setMail("syoda@tuyono.com");
		d1.setMemo("9999999");
		repository.saveAndFlush(d1);

		MyData d2 = new MyData();
		d2.setName("hanako");
		d2.setAge(15);
		d2.setMail("hanako@flower");
		d2.setMemo("08088888");
		repository.saveAndFlush(d2);

		MyData d3 = new MyData();
		d3.setName("sachiko");
		d3.setAge(37);
		d3.setMail("sachico@happy");
		d3.setMemo("070777");
		repository.saveAndFlush(d3);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index3");
		mav.addObject("msg","MyDataのサンプルです");
		Iterable<MyData> list = dao.getAll();
		mav.addObject("datalist", list);
		return mav;
	}
}
