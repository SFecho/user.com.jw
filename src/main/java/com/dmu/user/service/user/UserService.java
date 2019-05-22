package com.dmu.user.service.user;

import com.dmu.user.data.user.dao.UserDao;
import com.dmu.user.data.user.model.User;
import net.minidev.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Set;

@Service("userService")
public class UserService implements IUserService {
    @Resource
    UserDao userDao;

    protected static final Logger logger = LogManager.getLogger(UserService.class);

    @Override
    public User getUser(JSONObject param) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", param.getAsString("id"));
        hashMap.put("password", param.getAsString("password"));
        return userDao.getUser(hashMap);
    }

    @Override
    public void insertUser(JSONObject param) {
        Set<String> set = param.keySet();

        HashMap<String, String> hashMap = new HashMap<>();
        for(String key : set)
            hashMap.put(key, param.getAsString(key));
        logger.debug(hashMap.toString());
        userDao.insertUser(hashMap);
    }

}
