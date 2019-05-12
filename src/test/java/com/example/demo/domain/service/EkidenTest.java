package com.example.demo.domain.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.DeleteAll;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;

public class EkidenTest {

	@InjectMocks
	@Autowired
	EkidenService ekidenService;

	private static final String url = "jdbc:mysql://localhost:3306/sampledba?characterEncoding=UTF-8&serverTimezone=JST";
	private static final String user = "root";
	private static final String password = "tbK112001!";


	private static final Operation DELETE_ALL = DeleteAll.from("t_ekiden_order");

	private static final Operation insertAll = Insert.into("t_ekiden_order").columns("").values("").build();
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {

		Destination destination = new DriverManagerDestination(url, user, password);




	}


}
