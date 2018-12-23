package com.example.demo.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Shain;
import com.example.demo.domain.repository.ShainRepository;

@Service
@Transactional
public class ShainService {

	@Autowired
	ShainRepository repository;

	public List<Shain> findAll() {

		List<Shain> shains = repository.findAll();
		return shains;
	}

	public Shain find(Long id) {

		Optional<Shain> shain = repository.findById(id);
		return shain == null ? null : shain.get();
	}

	public Shain update(Shain shain) {
		return repository.save(shain);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	public Shain insertShain(Shain shain) {

		return repository.saveAndFlush(shain);
	}

	public void delete(Long id) {

		repository.deleteById(id);
	}
}
