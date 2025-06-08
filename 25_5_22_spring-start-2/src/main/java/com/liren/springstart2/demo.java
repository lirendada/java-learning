package com.liren.springstart2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {
    @RequestMapping("/liren")
    public String func() {
        return "lirendada~";
    }
}
