package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	@Override
	List<Profile> findAll();

}
