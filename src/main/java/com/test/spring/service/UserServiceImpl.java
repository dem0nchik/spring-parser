package com.test.spring.service;

import com.test.spring.dao.UserDao;
import com.test.spring.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserModel> getAll() {
        return userDao.getAll();
    }

    @Override
    public UserModel getOne(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public boolean add(UserModel user) {
        UserModel checkUser = getOne(user.getUsername());

        if(checkUser == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
            return true;
        } else {
            return false;
        }
    }


}
