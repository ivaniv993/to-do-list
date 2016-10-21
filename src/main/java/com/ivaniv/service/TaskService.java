package com.ivaniv.service;

import com.ivaniv.domain.Task;
import com.ivaniv.domain.TaskStatus;

import java.util.List;

/**
 * Created by iivaniv on 20.10.2016.
 */
public interface TaskService {

    List<Task> getAll();

    Task getById( Long id );

    void update(String query);

    void save(Task task);

    List<Task> getFinishedTasks();

    void updateStatus( Long id, TaskStatus status );


}
