package com.lnnu.studyhelper.controller;

import com.lnnu.studyhelper.service.user.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;

public class BaseController {
    @Resource
    IUserService userService;

    protected static final Logger logger = LogManager.getLogger(BaseController.class);
    protected static final String success = "{\"status\":\"success\"}";
    protected static final String failure = "{\"status\":\"failure\"}";
}
