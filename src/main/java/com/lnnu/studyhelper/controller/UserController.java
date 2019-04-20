package com.lnnu.studyhelper.controller;

import net.minidev.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    protected static final Logger logger = LogManager.getLogger(UserController.class);
    @RequestMapping(value = "/getclass", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String userJoinClass(@RequestBody JSONObject jsonObject, HttpServletRequest request){
        logger.info(jsonObject.toJSONString());
        JSONObject object = new JSONObject();
        object.put("username","Lee");
        object.put("openid","00001");
        logger.info(object.toJSONString());
        return object.toJSONString();
    }
}
