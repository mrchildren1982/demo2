package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.dto.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long>{



}
