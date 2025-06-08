package com.liren.ioc.controller;

import com.liren.ioc.model.MyListConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

//@Controller
public class demo6 {
    @Autowired
    private MyListConfig mylist;

    @PostConstruct
    public void init() {
        System.out.println(mylist);
    }
}
