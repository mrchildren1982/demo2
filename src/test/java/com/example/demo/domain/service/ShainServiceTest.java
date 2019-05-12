package com.example.demo.domain.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.DeleteAll;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;

public class ShainServiceTest {

	@Autowired
	@InjectMocks
	ShainService shainService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testDBSetup() throws Exception {

		Operation deleteAll  = DeleteAll.from("shain");
		Operation insert = Insert.into("shain")
				.row()
				.column("", "")
				.end()
				.build();

		Operation ope  = Operations.sequenceOf(deleteAll, insert);
		

	}


}
