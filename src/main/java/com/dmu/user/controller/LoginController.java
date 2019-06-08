package com.dmu.user.controller;

import com.dmu.user.data.user.model.User;
import net.minidev.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class LoginController extends BaseController {
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @PostMapping(value = "/dologin", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
    public User doLogin(@RequestBody JSONObject message, HttpServletRequest request){
        User user = userService.getUser(message);
        return user;
    }
}
