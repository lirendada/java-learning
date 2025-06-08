package com.liren.ioc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class UserConfig {
    public void func() {
        System.out.println("UserConfig");
    }
}
