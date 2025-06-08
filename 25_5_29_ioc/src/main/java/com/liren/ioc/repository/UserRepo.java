package com.liren.ioc.repository;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {
    public void func() {
        System.out.println("UserRepo");
    }
}
