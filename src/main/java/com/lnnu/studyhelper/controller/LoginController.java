package com.lnnu.studyhelper.controller;

import com.lnnu.studyhelper.data.model.User;
import com.lnnu.studyhelper.service.user.IUserService;
import net.minidev.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class LoginController extends BaseController {


    @RequestMapping(value = "/dologin", method =  RequestMethod.POST, produces = "application/json; charset=utf-8")
    String doLogin(@RequestBody JSONObject message, HttpServletRequest request){
        Map<String, String> userInfo = (Map<String, String>)message.get("userInfo");
        String openid = (String)message.get("openid");
        userInfo.put("openid", openid);
        logger.info(userInfo);
        User user = userService.getUser(userInfo);

        if(user == null){
            userService.insertUser(userInfo);
        }
        return success;
    }

}
