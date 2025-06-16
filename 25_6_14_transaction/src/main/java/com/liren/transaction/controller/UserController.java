package com.liren.transaction.controller;

import com.liren.transaction.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager; // 事务管理器

    @Autowired
    private TransactionDefinition transactionDefinition; // 事务属性

    @RequestMapping("/registry")
    public String registry(String name, String password) {
        // 1. 开启事务
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);

        // 2. 处理业务
        Integer aff_num = userService.insertUser(name, password);
        log.info("受影响行数：" + aff_num);

        // 如果出现了异常，并且没有捕获，则进行回滚
        //int a = 10 / 0;

        // 如果出现了异常，然后捕获了，则正常进行提交
        try {
            int a = 10 / 0;
        } catch (Exception e) {
            log.info("处理异常了~");
            // 当然也可以在捕获中手动回滚
            dataSourceTransactionManager.rollback(transaction);
            return "处理异常，进行了回滚";
        }

        // 3. 进行提交/回滚
        dataSourceTransactionManager.commit(transaction);
//        dataSourceTransactionManager.rollback(transaction);
        return "注册成功~";
    }
}
