package com.lnnu.studyhelper.data.dao;

import com.lnnu.studyhelper.data.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserDao {
    public User getUser(Map<String, String> params);
    public void insertUser(Map<String, String> param);
}