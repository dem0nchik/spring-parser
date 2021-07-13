package com.test.spring.dao;

import com.test.spring.models.FileResultModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileResultMapper implements RowMapper<FileResultModel> {
    @Override
    public FileResultModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        FileResultModel fileResultModel = new FileResultModel();

        fileResultModel.setFilename(rs.getString("filename"));
        fileResultModel.setFilepath(rs.getString("filepath"));
        fileResultModel.setUsername(rs.getString("username"));
        fileResultModel.setFileSize(rs.getLong("filesize"));
        fileResultModel.setDateAdding(rs.getDate("dateadding"));

        return fileResultModel;
    }
}
