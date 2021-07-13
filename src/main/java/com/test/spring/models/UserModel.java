package com.test.spring.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserModel {
    private int id;

    @NotEmpty(message = "Username should not be empty")
    @Size(min = 3, message = "Username must contains at least 3 letters")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 4, message = "Username must contains at least 4 symbols")
    private String password;

    public UserModel(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public UserModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
