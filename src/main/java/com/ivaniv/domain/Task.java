package com.ivaniv.domain;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by iivaniv on 20.10.2016.
 */
public class Task {

    private Long id;

    private int priority;

    private Timestamp deadLine;

    private String name;

    private TaskStatus status;

    public Task() {}

    public Task(int priority, Timestamp deadLine, String name) {
        this.priority = priority;
        this.deadLine = deadLine;
        this.name = name;
        this.status = TaskStatus.PLANNED;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Timestamp deadLine) {
        this.deadLine = deadLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
