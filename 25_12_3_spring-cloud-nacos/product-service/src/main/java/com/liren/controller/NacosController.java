package com.liren.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class NacosController {
    @Value("${nacos.config}")
    private String config;

    @RequestMapping("/getConfig")
    public String getConfig() {
        return "获取到nacos.config= " + config;
    }
}