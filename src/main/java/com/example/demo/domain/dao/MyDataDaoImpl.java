package com.example.demo.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.dto.MyData;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public MyDataDaoImpl() {
		super();
	}

	public MyDataDaoImpl(EntityManager manager) {
		this();
		entityManager = manager;
	}

	@Override
	public List<MyData> getAll() {

		Query query = entityManager.createQuery("from MyData");
		@SuppressWarnings("unchecked")
		List<MyData> list = query.getResultList();
		entityManager.close();
		return list;

	}

}
