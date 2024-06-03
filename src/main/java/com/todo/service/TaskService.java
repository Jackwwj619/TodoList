package com.todo.service;

import java.util.List;

import com.todo.DAO.TaskDAO;
import com.todo.bean.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TaskService {

    @Autowired
    TaskDAO TaskDAO;

    public Task getTaskById(long id){
        return TaskDAO.getTaskById(id);
    }

    public Task getTaskByTitle(String title){
        return TaskDAO.getTaskByTitle(title);
    }

    public void addTask(String title, String description, LocalDate dueDate){
        TaskDAO.addTask(title, description, dueDate);
    }

    public void updateTask(Task task){
        TaskDAO.updateTask(task);
    }

    public void deleteTask(){
        TaskDAO.deleteTask();
    }

    public List<Task> getAllTasks(){
        return TaskDAO.getAllTasks();
    }

    public List<Task> getPendingTasks(){
        return TaskDAO.getPendingTasks();
    }

    public List<Task> getCompletedTasks(){
        return TaskDAO.getCompletedTasks();
    }
}
