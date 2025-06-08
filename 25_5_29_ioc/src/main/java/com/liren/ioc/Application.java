package com.liren.ioc;

import com.liren.ioc.component.UserComponent;
import com.liren.ioc.configuration.UserConfig;
import com.liren.ioc.controller.UserController;
import com.liren.ioc.controller.UserController2;
import com.liren.ioc.model.User;
import com.liren.ioc.repository.UserRepo;
import com.liren.ioc.service.UserService;
import org.apache.naming.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan("com.liren.ioc.service")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static void main7(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        UserController2 userController = (UserController2)context.getBean("userController2");
        userController.func();
    }

    public static void main6(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        UserController userController = (UserController)context.getBean("userController");
        userController.func();
    }

    public static void main5(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        BeanFactory factory = new BeanFactory();

    }

    public static void main4(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        User u3 = (User)context.getBean("u3");
        User u4 = (User)context.getBean("u3");
        User u5 = (User)context.getBean("u4");
        System.out.println(u3);
        System.out.println(u4);
        System.out.println(u5);
    }

    public static void main3(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        UserComponent u1 = (UserComponent)context.getBean("u3");
        UserComponent u2 = (UserComponent)context.getBean("u2");
        UserComponent u3 = (UserComponent)context.getBean("user3");
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
    }

    public static void main2(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        UserComponent bean1 = context.getBean(UserComponent.class);
        bean1.func();
        UserComponent bean2 = (UserComponent)context.getBean("userComponent");
        bean2.func();

        System.out.println(bean1);
        System.out.println(bean2);
    }

    public static void main1(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        System.out.println(context.getId());
        System.out.println(context.getApplicationName());
        System.out.println(context.getDisplayName());
        System.out.println(context.getStartupDate());

        UserComponent bean1 = context.getBean(UserComponent.class);
        bean1.func();

        UserConfig bean2 = (UserConfig)context.getBean("userConfig");
        bean2.func();

        UserController bean3 = context.getBean(UserController.class);
        bean3.func();

        UserRepo bean4 = context.getBean(UserRepo.class);
        bean4.func();

        UserService bean5 = context.getBean(UserService.class);
        bean5.func();

    }

}
