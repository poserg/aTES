package com.github.poserg.ates.task_tracker.service;

import com.github.poserg.ates.task_tracker.persistence.model.Task;
import java.util.Optional;

public interface ITaskService {
    Optional<Task> findById(Long id);

    Task save(Task task);

    Iterable<Task> findAll();

}
