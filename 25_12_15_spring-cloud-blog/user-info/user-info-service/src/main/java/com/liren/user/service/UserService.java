package com.liren.user.service;


import com.liren.user.api.pojo.UserInfoRequest;
import com.liren.user.api.pojo.UserInfoResponse;
import com.liren.user.api.pojo.UserLoginResponse;

public interface UserService {
    UserLoginResponse login(UserInfoRequest user);

    UserInfoResponse getUserInfo(Integer userId);

    UserInfoResponse selectAuthorInfoByBlogId(Integer blogId);
}
