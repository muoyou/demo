package com.example.demo.service;

import com.example.demo.model.UserModel;

import java.util.Map;

public interface UserService {
    UserModel getUserInfoByUP(Map<String,Object> map);
}
