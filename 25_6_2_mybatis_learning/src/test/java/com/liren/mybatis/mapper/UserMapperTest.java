package com.liren.mybatis.mapper;

import com.liren.mybatis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectAll() {
        System.out.println(userMapper.selectAll());
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<String> result = names.stream()
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    void selectByID() {
        System.out.println(userMapper.selectByID(3));
    }

    @Test
    void selectByName() {
        System.out.println(userMapper.selectByName("zhangsan", "zhangsan123"));
    }

    @Test
    void insertUser() {
        User user = new User();
        user.setUsername("liren");
        user.setPassword("123123");
        user.setAge(18);
        Integer affect_num = userMapper.insertUser(user);
        System.out.println("受影响行数：" + affect_num + "，用户id为：" + user.getId());
    }

    @Test
    void deleteUser() {
        userMapper.deleteUser(5);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(6);
        user.setUsername("liren");
        user.setPassword("123123");
        user.setAge(30);
        user.setPhone("12306");
        user.setDeleteFlag(1);
        userMapper.updateUser(user);
    }
}