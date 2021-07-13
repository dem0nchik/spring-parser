package com.test.spring.dao;

import com.test.spring.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserModel getUserById(int id) {
        return jdbcTemplate.queryForObject("SELECT u_id as id, * FROM users WHERE id=?", new BeanPropertyRowMapper<>(UserModel.class), id);
    }

    public UserModel getUserByUsername(String username) {
        List<UserModel> users =
                jdbcTemplate.query("SELECT u_id as id, * FROM users WHERE username=?", new BeanPropertyRowMapper<>(UserModel.class), username);
        if(users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public void save(UserModel user) {
        jdbcTemplate.update("INSERT INTO users(username, password) VALUES(?, ?)", user.getUsername(), user.getPassword());
    }

    public List<UserModel> getAll() {
        return jdbcTemplate.query("SELECT u_id as id, * FROM users", new BeanPropertyRowMapper<>(UserModel.class));
    }
}
