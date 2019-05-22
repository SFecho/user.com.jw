package com.dmu.user.controller;

import com.dmu.user.service.content.IContentService;
import com.dmu.user.service.email.IEmailService;
import com.dmu.user.service.ftp.IFTPService;
import com.dmu.user.service.user.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BaseController {
    @Resource(name = "userService")
    IUserService userService;

    @Resource(name = "emailService")
    IEmailService emailService;

    @Resource(name = "ftpService")
    IFTPService ftpService;

    @Resource(name = "contentService")
    IContentService contentService;

    protected static final String success = "{\"status\":\"success\"}";
    protected static final String failure = "{\"status\":\"failure\"}";

    //bean
}
