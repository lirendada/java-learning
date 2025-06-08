package com.liren.ioc.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

//@Controller
public class demo3 {
    @Value("${string.s1}")
    private String s1;
    @Value("${string.s2}")
    private String s2;
    @Value("${string.s3}")
    private String s3;

    @PostConstruct
    public void init() {
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
