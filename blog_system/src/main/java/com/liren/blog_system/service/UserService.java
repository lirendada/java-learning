package com.liren.blog_system.service;


import com.liren.blog_system.model.response.LoginResponse;
import com.liren.blog_system.model.response.UserResponse;

public interface UserService {
    LoginResponse login(String userName, String password);

    UserResponse getUserInfo(Integer id);

    UserResponse getAuthorInfo(Integer id);
}
