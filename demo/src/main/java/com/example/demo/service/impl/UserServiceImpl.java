package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public UserModel getUserInfoByUP(Map<String, Object> map) {
        return userDao.getUserInfoByUP(map);
    }
}
