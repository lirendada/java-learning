package com.liren.springstart3.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class LoginController {
    @PostMapping(value="/login", produces="text/html")
    public String login(String name, String passwd, HttpSession session) {
        if(!StringUtils.hasLength(name) || !StringUtils.hasLength(passwd)) {
            return "false";
        }
        if(!"liren".equals(name) && !"123123".equals(passwd)) {
            return "false";
        }
        session.setAttribute("username", name);
        return "true";
    }

    @GetMapping(value="/getLoginUser", produces="text/html")
    public String getLoginUser(HttpSession session) {
        String username = (String)session.getAttribute("username");
        if(StringUtils.hasLength(username)) {
            return username;
        }
        return "";
    }
}
