package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.domain.dto.BexankShainDto;
import com.example.demo.domain.entity.BexankShainEntity;
import com.example.demo.domain.service.SessionDemoService;

/**
 * Springによるセッション管理
 *
 * @author tbk40
 *
 */
@RestController
@RequestMapping("/sessions")
//@SessionAttributes(types = BexankShainEntity.class)
public class SessionDemoController {

	@Autowired
	private SessionDemoService service;

	@Autowired
	private BexankShainDto bexankShainDto;
	@Autowired
	BexankShainEntity bexankShainEntity;

//	@ModelAttribute("bexankShainEntity")
//	public BexankShainEntity setUpBexankShainEntity() {
//
//		BexankShainEntity entity = new BexankShainEntity();
//		entity.setId("123");
//		entity.setName("ほげのほげお");
//		entity.setExp(new Integer(1203));
//
//		return new BexankShainEntity();
//	}


	@RequestMapping(path = "/getSession", method = RequestMethod.GET)
	public String getSession() {

		StringBuffer sb = new StringBuffer();

		bexankShainDto.setId("10000");
		bexankShainDto.setName("セッションスコープ");
		bexankShainDto.setExp(new Integer(12345));

		sb.append(bexankShainDto.getId());
		sb.append("\n");
		sb.append(bexankShainDto.getName());
		sb.append("\n");
		sb.append(bexankShainDto.getExp());
		return sb.toString();
	}

	@RequestMapping(path="/postSession", method = RequestMethod.POST)
	public BexankShainEntity post(SessionStatus sessionStatus) {

		System.out.println(bexankShainDto.getId());
		System.out.println(bexankShainDto.getName());
		System.out.println(bexankShainDto.getExp());

		BexankShainEntity result  = service.insert(bexankShainDto);
		sessionStatus.setComplete();
		return result;

	}



}
