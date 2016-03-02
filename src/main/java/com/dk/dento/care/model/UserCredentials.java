package com.dk.dento.care.model;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class UserCredentials implements Serializable {

    private Long id;

    @Email(message = "Please provide a valid email address.")
    @NotEmpty(message = "Email is required.")
    private String email;

    private String password;

    private String role;

    private boolean loginEnable;

    public UserCredentials() {

    }

    public UserCredentials(UserCredentials userCredentials) {

        this.id = userCredentials.id;
        this.email = userCredentials.email;
        this.password = userCredentials.password;
        this.role = userCredentials.role;
        this.loginEnable = userCredentials.loginEnable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isLoginEnable() {
        return loginEnable;
    }

    public void setLoginEnable(boolean loginEnable) {
        this.loginEnable = loginEnable;
    }
}
