package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.dto.MyData;
import com.example.demo.domain.repository.MyDataRepository;

@Controller
@RequestMapping("/")
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private MyDataRepository repository;


	@GetMapping(value="/hello")
	public ModelAndView index(@ModelAttribute("formModel") MyData mydata,
			ModelAndView mav) {

		mav.setViewName("index4");
		mav.addObject("msg", "this is sample content");
		Iterable<MyData> list = repository.findAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST, value="/hello")
	@Transactional(readOnly = false)
	public ModelAndView form(@ModelAttribute("formModel") @Validated MyData mydata,BindingResult result,ModelAndView mav) {

		ModelAndView res = null;
		if(!result.hasErrors()) {

			repository.saveAndFlush(mydata);
			res = new ModelAndView("rediret:/hello/");
		} else {

			mav.setViewName("index4");
			mav.addObject("msg", "Sorry, error is occured...");
			Iterable<MyData> list  = repository.findAll();
			mav.addObject("datalist",list);
			res = mav;
		}
		return res;
	}
}
