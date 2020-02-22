package com.example.demo.dao;

import com.example.demo.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 用户
 */
@Mapper
@Repository
public interface UserDao {

    UserModel getUserInfoByUP(Map<String,Object> map);
}
