package com.example.demo.model;


import java.io.Serializable;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    //账号
    private String username;
    //密码
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
