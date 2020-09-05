package com.back.alquiler.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledRun {

	@Autowired
	private JobLauncher launcher;

	@Autowired
	private Job RentaJob;

	@Scheduled(cron = "0 0 0 * * ?")
	// @Scheduled(cron="*/10 * * * * *")
	public void runRentaSchedule() throws JobExecutionException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		launcher.run(RentaJob,
				new JobParametersBuilder().addLong("timestamp", System.currentTimeMillis()).toJobParameters());

	}

}
