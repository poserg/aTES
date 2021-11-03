package com.github.poserg.ates.task_tracker.persistence.repository;

import com.github.poserg.ates.task_tracker.persistence.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ITaskRepository extends PagingAndSortingRepository<Task, Long> {
}
