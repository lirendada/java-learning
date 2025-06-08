package com.liren.mybatis.controller;

import com.liren.mybatis.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    public void getAllUser() {
        List<User> collect = userController.getAllUser().stream()
                .filter(x -> x.getId() == 2)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @BeforeEach
    void setUp() {
        System.out.println("before...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("after...");
    }
}