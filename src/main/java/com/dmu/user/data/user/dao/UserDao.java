package com.dmu.user.data.user.dao;

import com.dmu.user.data.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserDao {
    public User getUser(Map<String, String> params);
    public void insertUser(Map<String, String> param);
}