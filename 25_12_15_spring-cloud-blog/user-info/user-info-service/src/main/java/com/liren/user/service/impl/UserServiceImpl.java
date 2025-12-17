package com.liren.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liren.blog.api.BlogInfoApi;
import com.liren.common.exception.BlogException;
import com.liren.blog.api.pojo.BlogInfoResponse;
import com.liren.common.pojo.Result;
import com.liren.common.utils.*;
import com.liren.user.api.pojo.UserInfoRequest;
import com.liren.user.api.pojo.UserInfoResponse;
import com.liren.user.api.pojo.UserLoginResponse;
import com.liren.user.api.pojo.UserRegisterRequest;
import com.liren.user.dataobject.UserInfo;
import com.liren.user.mapper.UserInfoMapper;
import com.liren.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private BlogInfoApi blogInfoApi;

    @Autowired
    private RedisUtil redisUtil;

    private static final String USER_PREFIX = "user:";
    private static final long USER_EXPIRE_TIME = 60 * 60 * 24 * 2l; // 2天过期时间

    @Override
    public UserLoginResponse login(UserInfoRequest user) {
        // 验证账号密码是否正确
        // 先从缓存中获取用户信息，不存在再去数据库查询
        UserInfo userInfo = JsonUtil.toObject(redisUtil.get(USER_PREFIX + user.getUserName()), UserInfo.class);
        if (userInfo==null){
            userInfo = selectUserInfoByName(user.getUserName()); // 查数据库
            if(userInfo == null || userInfo.getId() == null) {
                throw new BlogException("用户不存在");
            } else {
                redisUtil.set(USER_PREFIX + user.getUserName(), JsonUtil.toJson(userInfo), USER_EXPIRE_TIME); // 重新写入缓存
            }
        }
//        if (!user.getPassword().equals(userInfo.getPassword())){
//            throw new BlogException("用户密码不正确");
//        }
        if (!SecurityUtil.verify(user.getPassword(),userInfo.getPassword())){
            throw new BlogException("用户密码不正确");
        }
        //账号密码正确的逻辑
        Map<String,Object> claims = new HashMap<>();
        claims.put("id", userInfo.getId());
        claims.put("name", userInfo.getUserName());

        String jwt = JWTUtils.genJwt(claims);
        return new UserLoginResponse(userInfo.getId(), jwt);
    }

    @Override
    public UserInfoResponse getUserInfo(Integer userId) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        UserInfo userInfo = selectUserInfoById(userId);
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    @Override
    public UserInfoResponse selectAuthorInfoByBlogId(Integer blogId) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        //1. 根据博客ID, 获取作者ID
        Result<BlogInfoResponse> blogInfoResponse = blogInfoApi.getBlogDeatail(blogId);
        //2. 根据作者ID, 获取作者信息
        if (blogInfoResponse == null){
            throw new BlogException("博客不存在");
        }
        UserInfo userInfo = selectUserInfoById(blogInfoResponse.getData().getUserId());
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    @Override
    public Integer register(UserRegisterRequest userResgisterRequest) {
        // 校验参数
        checkRegisterParams(userResgisterRequest);

        // 用户注册
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userResgisterRequest, userInfo);
        userInfo.setPassword(SecurityUtil.encrypt(userResgisterRequest.getPassword()));
        try {
            int result = userInfoMapper.insert(userInfo);
            if(result > 0) {
                // 插入数据库成功后，写入缓存
                redisUtil.set(USER_PREFIX + userInfo.getUserName(), JsonUtil.toJson(userInfo), USER_EXPIRE_TIME);
                return userInfo.getId();
            } else {
                throw new BlogException("注册失败");
            }
        } catch (Exception e) {
            log.error("用户注册失败，error={}", e);
            throw new BlogException("注册失败");
        }
    }

    private void checkRegisterParams(UserRegisterRequest userResgisterRequest) {
        // 校验用户名是否存在
        UserInfo userInfo = selectUserInfoByName(userResgisterRequest.getUserName());
        if(userInfo != null) {
            throw new BlogException("用户名已存在");
        }

        // 校验邮箱是否合法
        if(!RegexUtil.checkMail(userResgisterRequest.getEmail())) {
            throw new BlogException("邮箱格式不正确");
        }

        // 校验github地址是否合法
        if(!RegexUtil.checkURL(userResgisterRequest.getGithubUrl())) {
            throw new BlogException("github地址格式不正确");
        }
    }

    private UserInfo selectUserInfoByName(String userName) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserName, userName).eq(UserInfo::getDeleteFlag, 0));
    }

    private UserInfo selectUserInfoById(Integer userId) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getId, userId).eq(UserInfo::getDeleteFlag, 0));
    }
}
