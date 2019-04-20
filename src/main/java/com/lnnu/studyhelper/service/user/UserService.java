package com.lnnu.studyhelper.service.user;

import com.lnnu.studyhelper.data.dao.UserDao;
import com.lnnu.studyhelper.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("userService")
public class UserService implements IUserService {
    @Resource
    UserDao userDao;

    @Override
    public User getUser(Map<String, String> param) {
        return userDao.getUser(param);
    }

    @Override
    public void insertUser(Map<String, String> param) {
        userDao.insertUser(param);
    }
}
