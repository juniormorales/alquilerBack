package com.back.alquiler.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	public JobBuilderFactory jobbuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepbuilderFactory;
	
	@Bean
	public Tasklet StepRentaTasklet() {
		return new RentaStepTasklet();
	}
	
	@Bean
	public Step RentaStep() {
		return stepbuilderFactory
				.get("RentaStep")
				.tasklet(StepRentaTasklet())
				.build();
	}
	
	@Bean
	public Job AsistenciaJob(RentaJobListener listener) {
		return jobbuilderFactory
				.get("AsistenciaJob")
				.preventRestart()
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(RentaStep())
				.build();
	}
}
