package com.liren.test;

import com.liren.utils.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class jedis {
    private Jedis jedis = JedisConnectionFactory.getJedis();

//    @BeforeEach
//    public void init() {
//        // 1. 建立连接
//        jedis = new Jedis("127.0.0.1", 6379);
//        // 2. 设置密码
//        jedis.auth("123123");
//        // 3. 选择库
//        jedis.select(0);
//    }

    @AfterEach
    public void release() {
        // 需要判断一下是否为空，防止重复释放后报错
        if(jedis != null) {
            jedis.close();
        }
    }

    @Test
    public void testString(){
        String result = jedis.set("liren:1", "hhha");
        System.out.println("result = " + result);

        String s = jedis.get("liren:1");
        System.out.println(s);
    }

    @Test
    public void testHash() {
        jedis.hset("liren:user:1","name","Jack");
        jedis.hset("liren:user:2","name","Rose");
        jedis.hset("liren:user:1","age","21");
        jedis.hset("liren:user:2","age","18");

        Map<String, String> user1 = jedis.hgetAll("liren:user:1");
        System.out.println(user1);
        Map<String, String> user2 = jedis.hgetAll("liren:user:2");
        System.out.println(user2);
    }
}
