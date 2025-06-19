package com.liren.blog_system.controller;

import com.liren.blog_system.model.request.LoginRequest;
import com.liren.blog_system.model.response.LoginResponse;
import com.liren.blog_system.model.response.UserResponse;
import com.liren.blog_system.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userService;

    @RequestMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        log.info("login...");
        return userService.login(loginRequest.getUserName(), loginRequest.getPassword());
    }

    @RequestMapping("/getUserInfo")
    public UserResponse getUserInfo(@RequestParam("userId") @NotNull Integer id) {
        return userService.getUserInfo(id);
    }

    @RequestMapping("/getAuthorInfo")
    public UserResponse getAuthorInfo(@RequestParam("blogId")  @NotNull Integer id) {
        return userService.getAuthorInfo(id);
    }
}
