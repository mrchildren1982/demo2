package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.DataObject;
import com.example.demo.domain.dto.MyData;
import com.example.demo.domain.service.MyDataService;

@RestController
@RequestMapping("/rest")
public class MyDataRestController {


	String[] names = {
			"tuyano", "hanako", "taro", "sachiko", "ichiro"

	};
	String[] mails = {

			"syoda@tuyano.com", "hanako@flower", "taro@yamada", "sachiko@happy", "ichiro@baseball"
	};

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

	@RequestMapping(method = RequestMethod.GET,value = "/dataobject/{id}")
	public DataObject index(@PathVariable("id") int id) {

		if (id >= names.length) {
			id = id % names.length;
		}

		return new DataObject(id, names[id],mails[id]);
	}
}
