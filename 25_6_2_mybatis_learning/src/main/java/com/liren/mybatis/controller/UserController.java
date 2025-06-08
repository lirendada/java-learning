package com.liren.mybatis.controller;

import com.liren.mybatis.model.User;
import com.liren.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping("/getUserByID")
    public User getUserByID(Integer id) {
        return userService.getUserByID(id);
    }
}
