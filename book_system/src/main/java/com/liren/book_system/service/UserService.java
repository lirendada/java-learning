package com.liren.book_system.service;

import com.liren.book_system.mapper.UserInfoMapper;
import com.liren.book_system.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo login(String name, String password) {
        // service层做业务逻辑，比如判断账号密码是否合法
        // name是不可重复的，所以用name来查用户
        UserInfo userInfo = userInfoMapper.selectByName(name);
        if(userInfo == null || !userInfo.getPassword().equals(password)) {
            return null;
        }
        return userInfo;
    }
}
