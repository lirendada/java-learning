package com.liren.springstart3.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo2")
public class demo2 {
    @RequestMapping("/f1")
    public String f1(String key) {
        return "接收参数key: " + key;
    }

    @RequestMapping("/f2")
    public String f2(Integer n) {
        return "接收参数Integer: " + n;
    }

    @RequestMapping("/f3")
    public String f3(int n) {
        return "接收参数int: " + n;
    }
}
