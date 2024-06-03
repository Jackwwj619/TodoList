package com.todo.DAO;

import com.todo.DAO.I.TaskDAOInterface;
import com.todo.bean.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@Transactional
public class TaskDAO implements TaskDAOInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Task> taskMapper = new BeanPropertyRowMapper<>(Task.class);
    @Override
    public void addTask(String title, String description, LocalDate dueDate) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setCreatedDate(LocalDate.now());
        KeyHolder holder = new GeneratedKeyHolder();
        if (1 != jdbcTemplate.update((conn) -> {
            var ps = conn.prepareStatement("INSERT INTO tasks (title,description,dueDate,createdDate) VALUES(?, ?, ?, ?)");
            ps.setObject(1, task.getTitle());
            ps.setObject(2, task.getDescription());
            ps.setObject(3, task.getDueDate());
            ps.setObject(4, task.getCreatedDate());
            return ps;
        }, holder)) {
            throw new RuntimeException("insert failed.");
        }
    }

    public void deleteTask() {

    }

    public void updateTask(Task task) {
        if (1 != jdbcTemplate.update("UPDATE tasks SET title=?, description=?, dueDate=?, createdDate=?, isDone=? WHERE id=?", task.getTitle(), task.getDescription(), task.getDueDate(), task.getCreatedDate(), task.isDone(), task.getId())) {
            throw new RuntimeException("update failed.");
        }
    }


    public Task getTaskById(long id) {
        return jdbcTemplate.queryForObject(("select * from tasks where id = ?"), taskMapper, id);
    }

    public Task getTaskByTitle(String title) {
        return jdbcTemplate.queryForObject("select * from tasks where title = ?", taskMapper, title);
    }

    public List<Task> getAllTasks() {
        return jdbcTemplate.query("SELECT * FROM tasks", taskMapper);
    }

    public List<Task> getPendingTasks() {
        return jdbcTemplate.query("SELECT * FROM tasks WHERE status = ?", taskMapper, false);
    }

    public List<Task> getCompletedTasks() {
        return jdbcTemplate.query("SELECT * FROM tasks WHERE status = ?", taskMapper, true);
    }


}



