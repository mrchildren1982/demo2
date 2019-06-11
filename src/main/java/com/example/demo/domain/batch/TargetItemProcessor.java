package com.example.demo.domain.batch;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.domain.entity.SourceItemEntity;
import com.example.demo.domain.entity.TargetItemEntity;

public class TargetItemProcessor implements ItemProcessor<SourceItemEntity, TargetItemEntity> {

	@Override
	public TargetItemEntity process(SourceItemEntity item) throws Exception {
		if (item.isValid()) {
			return new TargetItemEntity(item.getName(), item.getValue());
		} else {
			return null;
		}
	}

}
