package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entity.jpa.TBatchHistory;

public interface TBatchHistoryRepository extends JpaRepository<TBatchHistory, Integer> {

}
