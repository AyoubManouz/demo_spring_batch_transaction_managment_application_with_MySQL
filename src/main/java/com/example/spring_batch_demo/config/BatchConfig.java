package com.example.spring_batch_demo.config;


import com.example.spring_batch_demo.entities.Transaction;
import com.example.spring_batch_demo.entities.TransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {

    public JobBuilderFactory jobBuilderFactory;
    public StepBuilderFactory stepBuilderFactory;
    private final ItemReader<TransactionDTO> itemReader;
    private final ItemProcessor<TransactionDTO, Transaction> itemProcessor;
    private final ItemWriter<Transaction> itemWriter;


    @Bean
    public Job job() {
        Step step = stepBuilderFactory.get("load-data-step")
                .<TransactionDTO, Transaction>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
        return jobBuilderFactory.get("transactions-job")
                .start(step)
                .build();
    }


}
