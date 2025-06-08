package com.liren.ioc.component;

import com.liren.ioc.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class UserComponent {
    public void func() {
        System.out.println("UserComponent");
    }

//    @Bean(name={"u1", "user3"})
    @Bean({"u1", "user3"})
    public UserComponent u1() {
        return new UserComponent();
    }

    @Bean
    public UserComponent u2() {
        return new UserComponent();
    }

//    @Primary
    @Bean
    public User u3() {
        return new User();
    }

    @Bean
    public User u4() {
        return new User();
    }
}
