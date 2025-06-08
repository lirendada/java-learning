package com.liren.springstart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo1 {
    @RequestMapping("/hello")
    public String func() {
        return "hello liren!";
    }
}
