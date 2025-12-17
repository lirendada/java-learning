package com.liren.user;

import com.liren.common.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void t1() {
        boolean set = redisUtil.set("test", "liren");
        String s = redisUtil.get("test");
        System.out.println(s);
    }
}
