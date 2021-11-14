package com.github.poserg.ates.task_tracker.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.poserg.ates.task_tracker.persistence.model.Task;
import com.github.poserg.ates.task_tracker.persistence.repository.ITaskRepository;
import com.github.poserg.ates.task_tracker.service.ITaskService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task save(Task task) {
        var savedTask = taskRepository.save(task);
        try {
            kafkaTemplate.send("tasks-stream", objectMapper.writeValueAsString(savedTask));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return savedTask;
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }
}
