package com.demo.spring.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by web on 20/04/17.
 */
public class LoginForm {
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

    @NotEmpty
    String username;
    @NotEmpty
    String password;
}

// User cannot leave username and password empty.