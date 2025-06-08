package com.liren.springstart3.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo5")
public class demo5 {
    @RequestMapping("/url/{id}/{name}")
    public String pathvar(@PathVariable Integer id,
                          @PathVariable(value="name") String username) {
        return "解析参数id：" + id + ", name：" + username;
    }
}
