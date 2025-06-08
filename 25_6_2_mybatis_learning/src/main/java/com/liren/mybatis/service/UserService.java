package com.liren.mybatis.service;

import com.liren.mybatis.mapper.UserMapper;
import com.liren.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUser() {
        return userMapper.selectAll();
    }

    public User getUserByID(Integer id) {
        return userMapper.selectByID(id);
    }
}
