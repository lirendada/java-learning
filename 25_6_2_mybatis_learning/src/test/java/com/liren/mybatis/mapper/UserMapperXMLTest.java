package com.liren.mybatis.mapper;

import com.liren.mybatis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperXMLTest {
    @Autowired
    private UserMapperXML userMapperXML;

    @Test
    void selectAll() {
        userMapperXML.selectAll()
                .stream()
                .forEach(x -> System.out.println(x));
    }

    @Test
    void insertUser() {
        User user = new User();
        user.setUsername("艾斯黛拉");
        user.setPassword("5135132kimo");
        user.setAge(25);
        user.setGender(1);
        user.setPhone("091823");
        Integer affect_num = userMapperXML.insertUser(user);
        System.out.println("受影响的行数：" + affect_num + "，id：" + user.getId());
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(4);
        user.setPassword("09102hn0");
        userMapperXML.updateUser(user);
    }

    @Test
    void deleteUser() {
        userMapperXML.deleteUser(2);
    }

    @Test
    void insertUserTestIf() {
        User user = new User();
        user.setUsername("艾斯黛拉");
        user.setPassword("5135132kimo");
        user.setAge(25);
        user.setGender(1);
        user.setPhone("091823");
        userMapperXML.insertUserTestIf(user);
    }

    @Test
    void selectUserTestWhere() {
        User user = new User();
//        user.setAge(25);
        user.setDeleteFlag(0);
        userMapperXML.selectUserTestWhere(user).stream().forEach(x -> System.out.println(x));
    }

    @Test
    void updateUserTestSet() {
        User user = new User();
        user.setAge(13);
        user.setDeleteFlag(1);
        user.setId(8);
        userMapperXML.updateUserTestSet(user);
    }

    @Test
    void deleteUserTestForeach() {
        List<Integer> list = Arrays.asList(4, 7);
        userMapperXML.deleteUserTestForeach(list);
    }

    @Test
    void selectUserTestInclude() {
        userMapperXML.selectUserTestInclude(3).stream().forEach(System.out::println);
    }
}