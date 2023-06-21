package com.example.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
public class batchConfiguration2 {

    @Bean
    public Job printSecond(JobRepository jobRepository, Step step2) {
        return new JobBuilder("PrintSecond", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step2)
                .build();
    }
    @Bean
    public Step step2(JobRepository jobRepository) {
        return new StepBuilder("step2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>>>>> Second Print <<<<<<");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
