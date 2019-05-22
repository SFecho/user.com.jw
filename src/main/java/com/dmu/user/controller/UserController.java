package com.dmu.user.controller;

import net.minidev.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @PostMapping(value = "/addUser", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
    public String addUser(@RequestBody JSONObject message, HttpServletRequest request){
        userService.insertUser(message);
        //bean
        return success;
    }

    @PostMapping(value = "/editHeader", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
    public String editHeader(@RequestBody MultipartFile file){
        return success;
    }
}
