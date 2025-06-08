package com.liren.ioc.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

//@Controller
public class demo4 {
    @Value("${boolean.value1}")
    private String value1;

    @Value("${boolean.value2}")
    private Boolean value2;

    @Value("${null.value}")
    private String value3;

    @PostConstruct
    public void init() {
        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);
    }
}
