package com.liren.ioc.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void func() {
        System.out.println("UserService");
    }
}
