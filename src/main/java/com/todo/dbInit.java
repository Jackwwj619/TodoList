package com.todo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class dbInit {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS tasks (" //
                + "id BIGINT IDENTITY NOT NULL PRIMARY KEY, " //
                + "title VARCHAR(100) NOT NULL, " //
                + "description VARCHAR(100) NOT NULL, " //
                + "dueDate DATE NOT NULL, " //
                + "status BOOLEAN NOT NULL, " //
                + "createdAt BIGINT NOT NULL)");
    }

//    public void init() {
//        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS users (" //
//                + "id BIGINT IDENTITY NOT NULL PRIMARY KEY, " //
//                + "email VARCHAR(100) NOT NULL, " //
//                + "password VARCHAR(100) NOT NULL, " //
//                + "name VARCHAR(100) NOT NULL, " //
//                + "createdAt BIGINT NOT NULL, " //
//                + "UNIQUE (email))");
//    }
}
