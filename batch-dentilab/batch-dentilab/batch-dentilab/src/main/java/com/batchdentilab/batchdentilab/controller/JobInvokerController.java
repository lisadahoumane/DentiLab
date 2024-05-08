package com.batchdentilab.batchdentilab.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class JobInvokerController {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job dentilabJob;
    @RequestMapping("/confirm")
    public String startJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParametersBuilder jobParameterBuilder = new JobParametersBuilder();
        jobParameterBuilder.addLocalTime("localtime", LocalTime.now());
        jobLauncher.run(dentilabJob, jobParameterBuilder.toJobParameters());
        return "Job done !";
    }
}
