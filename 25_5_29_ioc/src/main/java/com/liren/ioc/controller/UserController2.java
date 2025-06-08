package com.liren.ioc.controller;


import com.liren.ioc.component.UserComponent;
import com.liren.ioc.model.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class UserController2 {
//    @Qualifier("u3") // 指定对应Bean的名称
//    @Autowired
    @Resource(name = "u3")
    private User user;

    public void func() {
        System.out.println("UserController2");
    }
}
