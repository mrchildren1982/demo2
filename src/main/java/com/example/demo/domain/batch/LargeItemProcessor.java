package com.example.demo.domain.batch;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.domain.entity.LargeItemEntity;
import com.example.demo.domain.entity.TargetItemEntity;

public class LargeItemProcessor implements ItemProcessor<TargetItemEntity, LargeItemEntity> {

	@Override
	public LargeItemEntity process(TargetItemEntity item) throws Exception {

		return new LargeItemEntity(item.getName().toUpperCase(),
				item.getValue().toUpperCase());
	}

}
