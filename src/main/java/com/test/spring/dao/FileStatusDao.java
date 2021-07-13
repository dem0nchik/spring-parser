package com.test.spring.dao;

import com.test.spring.models.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FileStatusDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FileStatusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(int file_id, int user_id) {
        jdbcTemplate.update("INSERT INTO user_file_status(fr_id, u_id) VALUES(?, ?)", file_id, user_id);
    }
}
