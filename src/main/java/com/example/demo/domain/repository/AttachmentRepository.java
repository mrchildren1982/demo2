package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer>{

	@Override
	List<Attachment> findAll();


}
