package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.LargeItemEntity;

@Repository
public interface LargeItemRepository extends JpaRepository<LargeItemEntity, String> {

}
