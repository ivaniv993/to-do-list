package com.ivaniv.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ivaniv.domain.Task;
import com.ivaniv.domain.TaskStatus;
import com.ivaniv.service.TaskService;
import com.ivaniv.service.TaskServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by iivaniv on 20.10.2016.
 */
public class TaskController extends HttpServlet {

    private TaskService taskService;


    public void init() throws ServletException
    {
        taskService = new TaskServiceImpl();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        Date currentDate = new Date();
        List<Task> tasks = taskService.getAll();

        tasks.stream()
                .filter( t -> t.getStatus().equals(TaskStatus.PLANNED) && t.getDeadLine().before(currentDate) )
                .forEach( task -> task.setStatus(TaskStatus.OVERDUE));

        response.setContentType("application/json");
        String json = new Gson().toJson(tasks);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.valueOf(request.getParameter("id"));

        //check if task not overdue yet
        Task task = taskService.getById(id);
        if( task.getDeadLine().after(new Date())){
            taskService.updateStatus(id, TaskStatus.OVERDUE);
        }

        taskService.updateStatus(id, TaskStatus.FINISHED);


    }


    public void destroy() { }
}

