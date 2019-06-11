package com.example.demo.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.example.demo.domain.dto.Person;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	private static final Logger logger  = LoggerFactory.getLogger(PersonItemProcessor.class);

	@Override
	public Person process(Person person) throws Exception {

		final Person transformedPerson = new Person(person.getLastName().toUpperCase(), person.getFirstName().toUpperCase());

		logger.info("Converting(" + person + ") into (" + transformedPerson + ")");

		return transformedPerson;
	}

}
