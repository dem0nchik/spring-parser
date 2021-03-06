package com.test.spring.models;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private int id;

    @NotEmpty(message = "Username should not be empty")
    @Size(min = 3, message = "Username must contains at least 3 letters")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 4, message = "Username must contains at least 4 symbols")
    private String password;
}
