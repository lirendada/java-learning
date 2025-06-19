package com.liren.blog_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liren.blog_system.common.constants.Constants;
import com.liren.blog_system.common.exception.BlogException;
import com.liren.blog_system.common.utils.BeanTransUtils;
import com.liren.blog_system.common.utils.JWTUtils;
import com.liren.blog_system.mapper.BlogInfoMapper;
import com.liren.blog_system.mapper.UserInfoMapper;
import com.liren.blog_system.model.BlogInfo;
import com.liren.blog_system.model.UserInfo;
import com.liren.blog_system.model.response.LoginResponse;
import com.liren.blog_system.model.response.UserResponse;
import com.liren.blog_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public LoginResponse login(String userName, String password) {
        LambdaQueryWrapper<UserInfo> qw = new LambdaQueryWrapper<>();
        qw.eq(UserInfo::getUserName, userName)
                .eq(UserInfo::getDeleteFlag, Constants.BLOG_NORMAL);
        UserInfo userInfo = userInfoMapper.selectOne(qw);
        if(userInfo == null) {
            throw new BlogException("用户不存在");
        }

        // 走到这说明用户存在，则进行密码判断
        if(userInfo.getPassword().equals(password)) {
            Map<String, Object> claim = Map.of("name", userName, "id", userInfo.getId());
            String jwt = JWTUtils.createJWT(claim);
            return new LoginResponse(userInfo.getId(), jwt);
        } else {
            throw new BlogException("密码不正确");
        }
    }

    @Override
    public UserResponse getUserInfo(Integer id) {
        LambdaQueryWrapper<UserInfo> qw = new LambdaQueryWrapper<>();
        qw.eq(UserInfo::getId, id)
                .eq(UserInfo::getDeleteFlag, Constants.BLOG_NORMAL);
        UserInfo userInfo = userInfoMapper.selectOne(qw);
        if(userInfo == null) {
            throw new BlogException("获取用户信息失败~");
        }

        return BeanTransUtils.trans(userInfo);
    }

    @Override
    public UserResponse getAuthorInfo(Integer id) {
        // 获取id对应博客的作者userId
        LambdaQueryWrapper<BlogInfo> qw = new LambdaQueryWrapper<>();
        qw.eq(BlogInfo::getId, id)
                .eq(BlogInfo::getDeleteFlag, Constants.BLOG_NORMAL);
        BlogInfo blogInfo = blogInfoMapper.selectOne(qw);
        if(blogInfo == null) {
            throw new BlogException("获取博客信息失败~");
        }

        // 根据userId返回对应信息
        return getUserInfo(blogInfo.getUserId());
    }
}
