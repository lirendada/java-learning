package com.liren.springstart3.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo8")
public class demo8 {
    /**
     * 方式一：使用HttpServletRequest的getHeader()方法
     */
    @RequestMapping("/g1")
    public String getHeader1(HttpServletRequest req) {
        return "User-Agent: " + req.getHeader("User-Agent");
    }

    /**
     * 方式二：使用注解@RequestHeader
     */
    @RequestMapping("/g2")
    public String getHeader2(@RequestHeader("User-Agent") String agent) {
        return "User-Agent: " + agent;
    }
}
