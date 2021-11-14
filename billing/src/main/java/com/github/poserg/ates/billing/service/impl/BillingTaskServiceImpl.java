package com.github.poserg.ates.billing.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.poserg.ates.billing.persistence.model.Task;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingTaskServiceImpl {

    @Autowired
    private ObjectMapper objectMapper;

    private final Random random = new Random();

    @KafkaListener(topics = "tasks-stream", groupId = "whatisit")
    public void listenTasksStream(String message) throws JsonProcessingException {
        log.debug("Received Message: {}", message);
        Task task = objectMapper.readValue(message, Task.class);
        task.setCost((double) 20 + random.nextInt(20));
        log.debug("Task with cost: {}", task);
    }
}
