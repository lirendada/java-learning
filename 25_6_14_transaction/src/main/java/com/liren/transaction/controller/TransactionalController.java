package com.liren.transaction.controller;

import com.liren.transaction.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/trans")
public class TransactionalController {
    @Autowired
    private UserService userService;

    @Transactional
    @RequestMapping("/registry")
    public String registry(String name, String password) {
        userService.insertUser(name, password);
        try {
            int a = 10 / 0;
        } catch (Exception e) {
            log.info("处理异常了~");
            // 如果需要直接回滚，则需要手动调用
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return "注册成功";
    }

    @Transactional(rollbackFor = IOException.class)
    @RequestMapping("/registry2")
    public String registry2(String name, String password) throws IOException {
        userService.insertUser(name, password);
        if(true) {
            throw new IOException(); // 抛出了异常，但是不会回滚，需要指定rollbackFor才行
        }
        return "注册成功";
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @RequestMapping("/registry3")
    public String registry3(String name, String password) throws IOException {
        userService.insertUser(name, password);
        if(true) {
            throw new IOException(); // 抛出了异常，但是不会回滚，需要指定rollbackFor才行
        }
        return "注册成功";
    }
}
