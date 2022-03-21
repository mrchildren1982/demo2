package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entity.jpa.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
