package com.example.demo.domain.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort.Direction;

import com.example.demo.domain.entity.LargeItemEntity;
import com.example.demo.domain.entity.SourceItemEntity;
import com.example.demo.domain.entity.TargetItemEntity;
import com.example.demo.domain.repository.LargeItemRepository;
import com.example.demo.domain.repository.LargeItemRepositoryImpl;
import com.example.demo.domain.repository.SourceItemRepository;
import com.example.demo.domain.repository.SourceItemRepositoryImpl;
import com.example.demo.domain.repository.TargetItemRepository;
import com.example.demo.domain.repository.TargetItemRepositoryImpl;

//@Configuration
//@EnableBatchProcessing
public class SpringBatchSample1Configuration {
	@Autowired
	private SourceItemRepository sourceItemRepository;

	@Autowired
	private TargetItemRepository targetItemRepository;

	@Autowired
	private LargeItemRepository largeItemRepository;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Bean
	public SourceItemRepository sourceItemRepositoryImpl() {

		return new SourceItemRepositoryImpl();
	}

	@Bean
	public TargetItemRepository targetItemRepositoryImpl() {

		return new TargetItemRepositoryImpl();
	}

	@Bean
	public LargeItemRepositoryImpl largeItemRepositoryImpl() {

		return new LargeItemRepositoryImpl();
	}

	@Bean
	public ItemReader<SourceItemEntity> sourceItemReader() {
		RepositoryItemReader<SourceItemEntity> reader = new RepositoryItemReader<>();
		reader.setRepository(sourceItemRepositoryImpl());
		reader.setMethodName("findAll");
		Map<String, Direction> sort = new HashMap<>();
		sort.put("name", Direction.ASC);
		reader.setSort(sort);
		return reader;
	}

	@Bean
	public ItemReader<TargetItemEntity> targetItemReader() {

		RepositoryItemReader<TargetItemEntity> reader = new RepositoryItemReader<>();
		reader.setRepository(targetItemRepositoryImpl());
		reader.setMethodName("findAll");
		Map<String, Direction> sorts = new HashMap<>();
		reader.setSort(sorts);
		return reader;
	}

	@Bean
	public TargetItemProcessor targetItemProcessor() {
		return new TargetItemProcessor();
	}

	@Bean
	public ItemWriter<TargetItemEntity> targetItemWriter() {
		RepositoryItemWriter<TargetItemEntity> writer = new RepositoryItemWriter<>();
		writer.setRepository(targetItemRepositoryImpl());
		writer.setMethodName("save");
		return writer;
	}

	@Bean
	public Step sampleBatch1Step1() {

		return stepBuilderFactory.get("sampleBatch1Step1")
				.<SourceItemEntity, TargetItemEntity> chunk(Integer.MAX_VALUE)
				.reader(sourceItemReader())

				.processor(targetItemProcessor())
				.writer(targetItemWriter())
				.build();
	}

	@Bean
	public Step sampleBatch1Step2() {
		return stepBuilderFactory.get("sampleBatch1Step2")
				.<TargetItemEntity, LargeItemEntity> chunk(Integer.MAX_VALUE)
				.reader(targetItemReader())
				.processor(largeItemProcessor())
				.writer(largeItemWriter())
				.build();
	}

	@Bean
	public LargeItemProcessor largeItemProcessor() {

		return new LargeItemProcessor();
	}

	@Bean
	public ItemWriter<LargeItemEntity> largeItemWriter() {

		RepositoryItemWriter<LargeItemEntity> writer = new RepositoryItemWriter<>();
		writer.setRepository(largeItemRepositoryImpl());
		writer.setMethodName("save");
		return writer;

	}

	@Bean
	public Job sampleBatch1Job() {

		return jobBuilderFactory.get("sampleBat ch1Job")
				.incrementer(new RunIdIncrementer())
				.flow(sampleBatch1Step1())
				.next(sampleBatch1Step2())
				.end().build();
	}

}
