package com.test.spring.dao;

import com.test.spring.models.FileResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileResultDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FileResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FileResultModel> getAllResults() {
        return jdbcTemplate.query("SELECT u.username, ufs.date_adding as dateAdding, fr.file_name as fileName,\n" +
                "fr.file_size as fileSize, fr.file_path as filePath FROM users u\n" +
                "\tINNER JOIN user_file_status ufs ON ufs.u_id = u.u_id\n" +
                "\tINNER JOIN file_result fr ON fr.fr_id = ufs.fr_id", new FileResultMapper());
    }
}
