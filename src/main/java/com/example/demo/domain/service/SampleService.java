package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.dao.SampleDao;
import com.example.demo.domain.entity.SampleEntity;

@Service
@Transactional
public class SampleService {

//	@Autowired
//	SampleRepository sampleRepository;

	@Autowired
	SampleDao sampleDao;

	public List<SampleEntity> getSample() {
//		return sampleRepository.selectAll();

		return sampleDao.select();
	}

}
