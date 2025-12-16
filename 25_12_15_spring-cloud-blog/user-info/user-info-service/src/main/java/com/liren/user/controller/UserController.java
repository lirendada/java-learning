package com.liren.user.controller;

import com.liren.common.pojo.Result;
import com.liren.user.api.UserInfoApi;
import com.liren.user.api.pojo.UserInfoRequest;
import com.liren.user.api.pojo.UserInfoResponse;
import com.liren.user.api.pojo.UserLoginResponse;
import com.liren.user.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController implements UserInfoApi {
    @Autowired
    private UserService userService;

    @Override
    public Result<UserLoginResponse> login(@Validated @RequestBody UserInfoRequest user){
        log.info("用户登录, userName: {}", user.getUserName());
        return Result.success(userService.login(user));
    }

    @Override
    public Result<UserInfoResponse> getUserInfo(@NotNull @RequestParam("userId") Integer userId){
        return Result.success(userService.getUserInfo(userId));
    }

    @Override
    public Result<UserInfoResponse> getAuthorInfo(@NotNull @RequestParam("blogId") Integer blogId){
        return Result.success(userService.selectAuthorInfoByBlogId(blogId));
    }
}
