package com.liren.aop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
    @RequestMapping("/u1")
    public String u1(){
        log.info("执行u1");
        return "u1";
    }

    @RequestMapping("/u2")
    public String u2(){
        log.info("执行u2");
        return "u2";
    }
}
