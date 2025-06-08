package com.liren.ioc.controller;

import com.liren.ioc.model.MyApp;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class demo7 {
    @Autowired
    private MyApp app;

    @PostConstruct
    public void init() {
        System.out.println(app);
    }
}
