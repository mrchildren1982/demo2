package com.example.demo.domain.batch;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.domain.dto.Fruit;

public class FruitItemProcessor implements ItemProcessor<Fruit, Fruit> {

	@Override
	public Fruit process(Fruit item) throws Exception {

		return new Fruit(item.getName().toUpperCase(), item.getPrice());
	}

}
