package com.liren.springdataredis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String name;
    private Integer age;
}

@SpringBootTest
class ApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1() {
        redisTemplate.opsForValue().set("username", "liren");
        String username = (String)redisTemplate.opsForValue().get("username");
        System.out.println(username);
    }

    @Test
    public void test2() {
        redisTemplate.opsForValue().set("userdata", new User("liren", 123));
        User userdata = (User)redisTemplate.opsForValue().get("userdata");
        System.out.println(userdata);
    }

    @Test
    public void test3() {
        stringRedisTemplate.opsForValue().set("username", "lirendada");
        String s = stringRedisTemplate.opsForValue().get("username");
        System.out.println(s);
    }

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test4() throws JsonProcessingException {
        User user = new User("asond", 412);
        stringRedisTemplate.opsForValue().set("userdata2", mapper.writeValueAsString(user));

        String s = stringRedisTemplate.opsForValue().get("userdata2");
        System.out.println(s);
        User u = mapper.readValue(s, User.class);
        System.out.println(u);
    }
}
