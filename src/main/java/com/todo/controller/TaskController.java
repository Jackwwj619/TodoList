package com.todo.controller;


import com.todo.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Map;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleUnkonwException(Exception ex) {
        return new ModelAndView("500.html", Map.of("error", ex.getClass().getSimpleName(), "message", ex.getMessage()));
    }


    public ModelAndView index(HttpSession session) {
        Map<String, Object> model = Map.of("tasks", taskService.getAllTasks());
        return new ModelAndView("task.html", model);
    }

    @PostMapping("/add")
    public ModelAndView addTask(@RequestParam("title")String title, @RequestParam("description")String description, @RequestParam("dueDate") LocalDate dueDate) {
        try {
            taskService.addTask(title, description, dueDate);
        } catch (RuntimeException e) {
            return new ModelAndView("task.html", Map.of("error", "Add task failed."));
        }
        return new ModelAndView("redirect:/task");
    }

    @GetMapping("calender")
    public ModelAndView calender() {
        return new ModelAndView("calender.html");
    }

    @GetMapping("stati")
    public ModelAndView stati() {
        return new ModelAndView("stati.html");
    }
}
