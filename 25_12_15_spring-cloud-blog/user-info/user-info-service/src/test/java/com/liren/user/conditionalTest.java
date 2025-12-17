package com.liren.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.system.JavaVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

@SpringBootTest
public class conditionalTest {
    @Test
    public void testConditional() {
        System.out.println("条件装配测试");
    }
}

@Configuration
class AppConfig {

    @Bean
    @Conditional(Jdk17Condition.class)
    public JDK17 jdk17() {
        System.out.println("初始化jdk17");
        return new JDK17();
    }

    @Bean
    @Conditional(Jdk21Condition.class)
    public JDK21 jdk21() {
        System.out.println("初始化jdk21");
        return new JDK21();
    }
}

class JDK17 {}
class JDK21 {}

class Jdk17Condition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return JavaVersion.getJavaVersion().equals(JavaVersion.SEVENTEEN);
    }
}

class Jdk21Condition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return JavaVersion.getJavaVersion().equals(JavaVersion.TWENTY_ONE);
    }
}