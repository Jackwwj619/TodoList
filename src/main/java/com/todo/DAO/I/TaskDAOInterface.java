package com.todo.DAO.I;

import com.todo.bean.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskDAOInterface {
    public void addTask(String title, String description, LocalDate dueDate);
    public void deleteTask();
    public void updateTask(Task task);
    //public void markAsDone(); update方法即可
    //public Task getTask();
    public Task getTaskById(long id);
    public Task getTaskByTitle(String title);
    public List<Task> getAllTasks();
    public List<Task> getPendingTasks();
    public List<Task> getCompletedTasks();
}
