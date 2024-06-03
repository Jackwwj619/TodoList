package com.todo.controller;

import com.todo.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Map;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleUnknownException(Exception ex) {
        return new ModelAndView("500.html", Map.of("error", ex.getClass().getSimpleName(), "message", ex.getMessage()));
    }

    @GetMapping("/task")
    public ModelAndView index() {
        Map<String, Object> model = Map.of("tasks", taskService.getAllTasks());
        return new ModelAndView("task.html", model);
    }
//    public String test() {
//        return "Hello World";
//    }

    @PostMapping("/add")
    public ModelAndView addTask(@RequestParam("title") String title,
                                @RequestParam("description") String description,
                                @RequestParam("dueDate") LocalDate dueDate) {
        taskService.addTask(title, description, dueDate);
        return new ModelAndView("redirect:/task");
    }

    @GetMapping("/calender")
    public ModelAndView calender() {
        return new ModelAndView("calender.html");
    }

    @GetMapping("/stati")
    public ModelAndView stati() {
        return new ModelAndView("stati.html");
    }
}
