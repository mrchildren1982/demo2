package com.example.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.BexankShain;

@Repository
public interface BexankShainRepository extends JpaRepository<BexankShain, Long> {

	@Override
	Optional<BexankShain> findById(Long id);

	@Override
	List<BexankShain> findAll();

	@Override
	BexankShain save(BexankShain shain);

	@Override
	BexankShain saveAndFlush(BexankShain shain);

	@Override
	public void deleteAll();

	@Override
	public void deleteById(Long id);

}
