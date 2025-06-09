package com.liren.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.liren.mybatisplus.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void insert() {
        UserInfo user = new UserInfo();
        user.setAge(123);
        user.setPhone("123124");
        user.setPassword("125412351235");
        userInfoMapper.insert(user);
    }

    @Test
    public void select() {
        System.out.println(userInfoMapper.selectById(1));
    }

    @Test
    public void select2() {
        QueryWrapper<UserInfo> userInfoWrapper = new QueryWrapper<UserInfo>()
                .select("id, username, password, age")
                .eq("age", 18)
                .like("username", "min");
        List<UserInfo> userInfos = userInfoMapper.selectList(userInfoWrapper);
        userInfos.stream().forEach(System.out::println);
    }

    @Test
    public void select3() {
        LambdaQueryWrapper<UserInfo> userInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInfoLambdaQueryWrapper.select(UserInfo::getId, UserInfo::getUsername,
                                        UserInfo::getPassword, UserInfo::getAge)
                .eq(UserInfo::getAge, 25)
                .like(UserInfo::getUsername, "min");
        userInfoMapper.selectList(userInfoLambdaQueryWrapper)
                .stream().forEach(System.out::println);
    }

    @Test
    public void update() {
        UserInfo userInfo = new UserInfo();
        userInfo.setDeleteFlag(1);

        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.lt("age", 20);
        userInfoMapper.update(userInfo, userInfoQueryWrapper);
    }

    @Test
    public void update2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setDeleteFlag(0);
        userInfo.setAge(5);

        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.in("id", List.of(6, 9));
        userInfoMapper.update(userInfo, userInfoQueryWrapper);
    }

    @Test
    public void update3() {
        UpdateWrapper<UserInfo> userInfoUpdateWrapper = new UpdateWrapper<UserInfo>()
                .set("delete_flag", 1)
                .set("age", 15)
                .in("id", List.of(6, 11));
        userInfoMapper.update(userInfoUpdateWrapper);
    }

    @Test
    public void update4() {
        UpdateWrapper<UserInfo> userInfoUpdateWrapper = new UpdateWrapper<UserInfo>()
                .setSql("age = age + 10")
                .in("id", List.of(6, 9, 11));
        userInfoMapper.update(userInfoUpdateWrapper);
    }

    @Test
    public void update5() {
        LambdaUpdateWrapper<UserInfo> userInfoLambdaUpdateWrapper = new LambdaUpdateWrapper<UserInfo>()
                .setSql("age = age + 10")
                .in(UserInfo::getId, List.of(11));
        userInfoMapper.update(userInfoLambdaUpdateWrapper);
    }

    @Test
    public void delete() {
        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<UserInfo>()
                .eq("age", 18);
        userInfoMapper.delete(userInfoQueryWrapper);
    }

    @Test
    void mySelect() {
        LambdaQueryWrapper<UserInfo> userInfoQueryWrapper = new LambdaQueryWrapper<>();
        userInfoQueryWrapper.eq(UserInfo::getId, 6);
        userInfoMapper.MySelect(userInfoQueryWrapper).forEach(System.out::println);
    }
}