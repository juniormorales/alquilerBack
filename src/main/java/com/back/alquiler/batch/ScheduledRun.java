package com.back.alquiler.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
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
 
    private JobExecution execution;
     
    @Scheduled(cron="0 0 0 * * ?")
    //@Scheduled(cron="*/10 * * * * *")
    public void runRentaSchedule(){
        try {
        	execution = launcher.run(RentaJob, new JobParametersBuilder()
        			.addLong("timestamp",System.currentTimeMillis())
        			.toJobParameters());
            System.out.println("Execution status: "+ execution.getStatus());
        } catch (JobExecutionAlreadyRunningException e) {
        	e.printStackTrace();
        } catch (JobRestartException e) {   
        	e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {    
        	e.printStackTrace();
        } catch (JobParametersInvalidException e) {  
        	e.printStackTrace();
        }
    }
    
}
