package com.example.spring_batch_demo.config;


import com.example.spring_batch_demo.entities.Transaction;
import com.example.spring_batch_demo.repositories.TransactionRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TransactionWriterConfig implements ItemWriter<Transaction> {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionWriterConfig(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void write(List<? extends Transaction> items){
        transactionRepository.saveAll(items);
    }
}