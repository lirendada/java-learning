package com.liren.transaction.service;

import com.liren.transaction.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public Integer insertUser(String name, String password) {
        return userInfoMapper.insertUser(name, password);
    }
}
