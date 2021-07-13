package com.test.spring.service;

import com.test.spring.models.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> getAll();
    UserModel getOne(String username);
    boolean add(UserModel user);
}
