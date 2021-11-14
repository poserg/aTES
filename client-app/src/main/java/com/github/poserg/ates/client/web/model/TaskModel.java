package com.github.poserg.ates.client.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {
    private Long id;
    private String name;
    private String description;
    private String status;
}