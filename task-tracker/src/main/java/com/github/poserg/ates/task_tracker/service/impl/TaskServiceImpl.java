package com.github.poserg.ates.task_tracker.service.impl;

import com.github.poserg.ates.task_tracker.persistence.model.Task;
import com.github.poserg.ates.task_tracker.persistence.repository.ITaskRepository;
import com.github.poserg.ates.task_tracker.service.ITaskService;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements ITaskService {

    private ITaskRepository taskRepository;

    public TaskServiceImpl(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }
}
