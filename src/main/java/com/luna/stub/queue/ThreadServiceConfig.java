package com.luna.stub.queue;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadServiceConfig {
    @Autowired
    ThreadService threadService;
    @PostConstruct
    public void startThreadService() {
        Thread thread = new Thread(() -> {
            threadService.runMessage();
        });
        thread.start();
    }
}
