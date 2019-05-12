package com.example.demo.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.dao.BexankShainDao;
import com.example.demo.domain.dto.BexankShainDto;
import com.example.demo.domain.entity.BexankShainEntity;

@Service
@Transactional
public class SessionDemoService {

	@Autowired
	private BexankShainDao bexankShainDao;

	public BexankShainEntity insert(BexankShainDto bexnakShainDto) {

		BexankShainEntity entity = new BexankShainEntity();
		BeanUtils.copyProperties(bexnakShainDto, entity);
		int insert = bexankShainDao.insert(entity);
		if (insert == 0) {
			return null;
		} else {
			return entity;
		}

	}
}
