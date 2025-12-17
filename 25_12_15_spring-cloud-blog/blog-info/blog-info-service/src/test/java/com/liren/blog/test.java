package com.liren.blog;

import com.liren.common.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void t1() {
        System.out.println(redisUtil.get("test"));
    }
}
