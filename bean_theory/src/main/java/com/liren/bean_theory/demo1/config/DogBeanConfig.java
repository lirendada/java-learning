package com.liren.bean_theory.demo1.config;

import com.liren.bean_theory.demo1.model.Dog;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
public class DogBeanConfig {
    @Bean // 默认是单例singleton
    public Dog dog(){
        Dog dog = new Dog();
        dog.setName("旺旺");
        return dog;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Dog singleDog(){
        Dog dog = new Dog();
        return dog;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Dog prototypeDog(){
        Dog dog = new Dog();
        return dog;
    }

    @Bean
    @RequestScope
    public Dog requestDog() {
        Dog dog = new Dog();
        return dog;
    }

    @Bean
    @SessionScope
    public Dog sessionDog() {
        Dog dog = new Dog();
        return dog;
    }

    @Bean
    @ApplicationScope
    public Dog applicationDog() {
        Dog dog = new Dog();
        return dog;
    }
}
