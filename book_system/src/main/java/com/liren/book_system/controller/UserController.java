package com.liren.book_system.controller;

import com.liren.book_system.model.UserInfo;
import com.liren.book_system.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Boolean login(String name, String password, HttpSession session) {
        // controller只做 “接收请求 + 参数校验”
        if(!StringUtils.hasLength(name) ||
           !StringUtils.hasLength(password)) {
            return false;
        }
        UserInfo userInfo = userService.login(name, password);
        if(userInfo != null) {
            session.setAttribute("user", userInfo); // ✅ 存储会话：由 Controller 负责
            return true;
        } else {
            return false;
        }
    }
}
