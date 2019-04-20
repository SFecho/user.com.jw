package com.lnnu.studyhelper.service.user;

import com.lnnu.studyhelper.data.model.User;

import java.util.Map;

public interface IUserService {
    public User getUser(Map<String, String> param);
    public void insertUser(Map<String, String> param);
}
