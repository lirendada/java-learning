package com.liren.user.api;


import com.liren.common.pojo.Result;
import com.liren.user.api.pojo.UserInfoRequest;
import com.liren.user.api.pojo.UserInfoResponse;
import com.liren.user.api.pojo.UserLoginResponse;
import com.liren.user.api.pojo.UserRegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-info-service", path = "/user")
public interface UserInfoApi {
    @RequestMapping("/login")
    Result<UserLoginResponse> login(@RequestBody UserInfoRequest user);

    @RequestMapping("/getUserInfo")
    Result<UserInfoResponse> getUserInfo(@RequestParam("userId") Integer userId);

    @RequestMapping("/getAuthorInfo")
    Result<UserInfoResponse> getAuthorInfo(@RequestParam("blogId") Integer blogId);

    @RequestMapping("/register")
    Result<Integer> register(@RequestBody UserRegisterRequest userResgisterRequest);
}
