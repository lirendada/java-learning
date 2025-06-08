package com.liren.ioc.controller;

import com.liren.ioc.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller
public class demo5 {
//    @Autowired
    public Student stu;

    @PostConstruct
    public void init() {
        System.out.println(stu);
    }
}
