package com.github.poserg.ates.task_tracker.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private long id;
    private String name;
    private String description;
    private String status;
}