package com.batchdentilab.batchdentilab;

import com.batchdentilab.batchdentilab.context.DentilabContext;
import com.batchdentilab.batchdentilab.step.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan(basePackages = {"com.batchdentilab.batchdentilab"})
public class DentiLabConfiguration {
    @Bean
    public Job dentilabJob(JobRepository jobRepository,
                           @Qualifier("FileWritingStep") Step writingFile,
                           @Qualifier("SendingEmailStep") Step sendingEmail,
                           @Qualifier("InitDentilabTask") Step initDentilab,
                           @Qualifier("ItemReaderTask") Step itemReader,
                           @Qualifier("DBWritingStep") Step dbWriting){
        return new JobBuilder("dentilabJob", jobRepository)
                .start(initDentilab)
                .next(itemReader)
                .next(writingFile)
                .next(dbWriting)
                .next(sendingEmail)
                .build();
    }

    @Bean("InitDentilabTask")
    public Step stepInitDentilabTask(JobRepository jobRepository,
                                    PlatformTransactionManager transactionManager){
        StepBuilder stepBuilder = new StepBuilder("initDentilabStep", jobRepository);
        InitDentilabTask initDentilabTask = new InitDentilabTask();
        return stepBuilder.tasklet(initDentilabTask, transactionManager).allowStartIfComplete(true).build();
    }
    @Bean("ItemReaderTask")
    public Step stepItemReaderTask(JobRepository jobRepository,
                                     PlatformTransactionManager transactionManager){
        StepBuilder stepBuilder = new StepBuilder("itemReaderStep", jobRepository);
        ItemReaderTask itemReaderTask = new ItemReaderTask();
        return stepBuilder.tasklet(itemReaderTask, transactionManager).allowStartIfComplete(true).build();
    }
    @Bean("fileWritingTask")
    public FileWritingTask fileWritingTask(){
        return new FileWritingTask();
    }
    @Bean("FileWritingStep")
    public Step stepFileWritingTask(JobRepository jobRepository,
                                    @Qualifier("fileWritingTask") Tasklet fileWritingTask,
                                    PlatformTransactionManager transactionManager){
        StepBuilder stepBuilder = new StepBuilder("writingTaskStep", jobRepository);
        return stepBuilder.tasklet(fileWritingTask, transactionManager).allowStartIfComplete(true).build();
    }
    @Bean("dbWritingTask")
    public DBWritingTask dbWritingTask(){
        return new DBWritingTask();
    }
    @Bean("DBWritingStep")
    public Step stepDBWritingTask(JobRepository jobRepository,
                                    @Qualifier("dbWritingTask") Tasklet dbWritingTask,
                                    PlatformTransactionManager transactionManager){
        StepBuilder stepBuilder = new StepBuilder("DBWritingStep", jobRepository);
        return stepBuilder.tasklet(dbWritingTask, transactionManager).allowStartIfComplete(true).build();
    }

    @Bean("sendEmailTask")
    public SendEmailTask sendEmailTask(){
        return new SendEmailTask();
    }
    @Bean("SendingEmailStep")
    public Step stepSendEmail(JobRepository jobRepository,
                                    @Qualifier("sendEmailTask") Tasklet sendEmailTask,
                                    PlatformTransactionManager transactionManager){
        StepBuilder stepBuilder = new StepBuilder("sendingEmailStep", jobRepository);
        return stepBuilder.tasklet(sendEmailTask, transactionManager).allowStartIfComplete(true).build();
    }
}
