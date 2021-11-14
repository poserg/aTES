package com.github.poserg.ates.task_tracker.web.controller;

import com.github.poserg.ates.task_tracker.persistence.model.Task;
import com.github.poserg.ates.task_tracker.service.ITaskService;
import com.github.poserg.ates.task_tracker.web.dto.TaskDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/tasks")
@Slf4j
public class TaskController {

    private ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @CrossOrigin(origins = "http://localhost:8089")
    @GetMapping(value = "/{id}")
    public TaskDto findOne(@PathVariable Long id) {
        Task entity = taskService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convertToDto(entity);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody TaskDto newTask) {
        Task entity = convertToEntity(newTask);
        this.taskService.save(entity);
    }

    @GetMapping
    public Collection<TaskDto> findAll() {
        Iterable<Task> tasks = this.taskService.findAll();
        List<TaskDto> tasksDtos = new ArrayList<>();
        tasks.forEach(p -> tasksDtos.add(convertToDto(p)));
        log.debug("Return tasks: {}", tasksDtos);
        return tasksDtos;
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable("id") Long id, @RequestBody TaskDto updatedTask) {
        Task taskEntity = convertToEntity(updatedTask);
        return this.convertToDto(this.taskService.save(taskEntity));
    }

    protected TaskDto convertToDto(Task entity) {
        return new TaskDto(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getStatus());
    }

    protected Task convertToEntity(TaskDto dto) {
        Task task = new Task(dto.getTitle());
        if (!StringUtils.isEmpty(dto.getId())) {
            task.setId(dto.getId());
        }
        return task;
    }
}