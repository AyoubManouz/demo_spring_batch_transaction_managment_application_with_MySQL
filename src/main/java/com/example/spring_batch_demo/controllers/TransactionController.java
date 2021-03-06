package com.example.spring_batch_demo.controllers;



import com.example.spring_batch_demo.config.Launcher;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TransactionController {

    private final Launcher jobLauncher;

    @Autowired
    public TransactionController(Launcher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    @GetMapping("/launch")
    public Map<String, String> startJob() throws Exception {
        Map<String, String> map = new HashMap<>();
        JobExecution jobExecution = jobLauncher.launchJob();
        map.put("Status", jobExecution.getStatus().toString());
        map.put("Date", Date.from(Instant.now()).toString());
        return map;
    }
}
