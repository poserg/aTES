package com.github.poserg.ates.task_tracker.web.dto;

public class TaskDto {
    private long id;
    private String name;

    public TaskDto() {
        super();
    }

    public TaskDto(final long id, final String name) {
        super();

        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}