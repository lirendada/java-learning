package com.liren.ioc.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

//@Controller
public class demo2 {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String name; // 不需要和键同名

    @Value("${spring.datasource.password}")
    private String passwd; // 不需要和键同名

    @Value("${mykey1}")
    private String mykey; // 不需要和键同名

    @PostConstruct
    public void init() {
        System.out.println(url);
        System.out.println(name);
        System.out.println(passwd);
        System.out.println(mykey);
    }
}
