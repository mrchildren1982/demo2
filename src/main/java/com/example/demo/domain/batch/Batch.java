package com.example.demo.domain.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.domain.dto.Fruit;

//@Configuration
//@EnableBatchProcessing
public class Batch {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Bean
	public FlatFileItemReader<Fruit> reader() {

		FlatFileItemReader<Fruit> reader = new FlatFileItemReader<Fruit>();
		reader.setResource(new ClassPathResource("fruit_price.csv"));
		reader.setLineMapper(new DefaultLineMapper<Fruit>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "name", "price" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Fruit>() {
					{
						setTargetType(Fruit.class);
					}
				});
			}
		});

		return reader;
	}

	@Bean
	public FruitItemProcessor processor() {
		return new FruitItemProcessor();
	}

	// Writer
	@Bean
	public JdbcBatchItemWriter<Fruit> writer() {
		JdbcBatchItemWriter<Fruit> writer = new JdbcBatchItemWriter<Fruit>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Fruit>());
		writer.setSql("INSERT INTO fruit (name, price) VALUES (:name, :price)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobStartEndListener(new JdbcTemplate(dataSource));
	}

	@Bean
	public Step step1() {

		return stepBuilderFactory.get("step1")
				.<Fruit, Fruit> chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();

	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.<Fruit, Fruit> chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}

	// ジョブ
	@Bean
	public Job testJob() {
		return jobBuilderFactory.get("testJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.flow(step1())
				.next(step2())
				.end()
				.build();
	}

}
