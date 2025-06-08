package com.liren.ioc.controller;

import com.liren.ioc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
//    @Autowired
//    private UserService userService;

//    private UserService userService;
//
//    public UserController() {}
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void func() {
        System.out.println("UserController");
        userService.func();
    }
}
