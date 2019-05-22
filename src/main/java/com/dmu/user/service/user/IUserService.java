package com.dmu.user.service.user;

import com.dmu.user.data.user.model.User;
import net.minidev.json.JSONObject;

public interface IUserService {
    public User getUser(JSONObject param);
    public void insertUser(JSONObject param);

}
