package com.dmu.user.controller;

import net.minidev.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/io")
public class IOController extends BaseController{
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @PostMapping(value = "/getpath", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
    String getFilePath(@RequestBody JSONObject message, HttpServletRequest request){
        String path = contentService.getPath("ddd");

        logger.debug(path);

        return success;
    }
}
