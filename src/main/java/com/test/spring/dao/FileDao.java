package com.test.spring.dao;

import com.test.spring.models.FileModel;
import com.test.spring.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.HashMap;
import java.util.List;

@Repository
public class FileDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public FileModel getByFilepath(String filepath) {
        List<FileModel> files =
                jdbcTemplate.query("SELECT fr_id as id, * FROM file_result WHERE file_path=?", new BeanPropertyRowMapper<>(FileModel.class), filepath);
        if(files.size() == 0) {
            return null;
        } else {
            return files.get(0);
        }
    }

    public void saveResultInDb(FileModel file) {
        jdbcTemplate.update("INSERT INTO file_result(file_name, file_size, file_path) VALUES(?, ?, ?)",
            file.getName(), file.getSize(), file.getFilePath());
    }

    public void saveResultInFile(HashMap<String, Integer> map, String filepath) {
        try (FileOutputStream fos = new FileOutputStream(filepath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)
        ){
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, Integer> getResultFromFile(String filepath) {
        HashMap<String, Integer> retrieved = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(filepath);
             ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            retrieved = (HashMap<String, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return retrieved;
    }
}
