package com.ivaniv.controller;

import com.ivaniv.domain.Task;
import com.ivaniv.service.TaskService;
import com.ivaniv.service.TaskServiceImpl;
import com.ivaniv.validation.TaskValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateController extends HttpServlet {

    private TaskService taskService;

    public void init() throws ServletException
    {
        taskService = new TaskServiceImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        String taskName = request.getParameter("name");
        String taskPriority = request.getParameter("priority");
        String taskDate = request.getParameter("date");

        Task task = TaskValidator.validate(taskPriority, taskName, taskDate);
        if (task == null) {
            response.sendRedirect("task.jsp");
            return;
        }
        taskService.save(task);
        response.sendRedirect("list.jsp");
    }
}
