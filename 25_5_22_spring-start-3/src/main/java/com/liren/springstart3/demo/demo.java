package com.liren.springstart3.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {
    @RequestMapping("hello")
    public String func() {
        return "liren!";
    }

    @GetMapping("/r1")
    public String r1() {
        return "get";
    }

    @PostMapping("/r1")
    public String r2() {
        return "post";
    }
}
