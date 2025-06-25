package com.liren.bean_theory;

import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BeanTheoryApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BeanTheoryApplication.class, args);
        System.out.println("lirendada: " + applicationContext.getApplicationName());
        System.out.println(applicationContext.getDisplayName());
        System.out.println(applicationContext.getId());
    }

}
