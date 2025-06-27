package com.liren.bean_theory;

import com.liren.third.MyConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class BeanTheoryApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        MyConfig myConfig = applicationContext.getBean(MyConfig.class);
        System.out.println(myConfig);
    }
}
