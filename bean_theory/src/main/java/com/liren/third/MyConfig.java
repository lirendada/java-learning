package com.liren.third;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class MyConfig {
    public void func() {
        System.out.println("start func() ...");
    }
}
