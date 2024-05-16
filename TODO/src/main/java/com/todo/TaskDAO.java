package com.todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/todo_app";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";

    private static final String INSERT_TASKS_SQL = "INSERT INTO tasks (title) VALUES (?);";
    private static final String SELECT_ALL_TASKS = "SELECT * FROM tasks;";
    private static final String UPDATE_TASK_SQL = "UPDATE tasks SET status = ? WHERE task_id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertTask(String title) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASKS_SQL)) {
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> selectAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TASKS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("task_id");
                String title = rs.getString("title");
                boolean status = rs.getBoolean("status");
                tasks.add(new Task(id, title, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void updateTaskStatus(int taskId, boolean status) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK_SQL)) {
            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, taskId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
