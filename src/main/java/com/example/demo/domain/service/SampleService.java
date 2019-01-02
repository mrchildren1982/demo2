package com.example.demo.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.SampleEntity;
import com.example.demo.domain.repository.SampleRepository;

@Service
@Transactional
public class SampleService {

//	@Autowired
	SampleRepository sampleRepository;

	public List<SampleEntity> getSample() {
		return sampleRepository.selectAll();
	}

}
