package com.liren.bean_theory;

import com.liren.third.EnableMyConfig;
import com.liren.third.MyConfig;
import com.liren.third.MyImportSelector;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * 方式一：使用@ComponentScan扫描路径
 */
//@ComponentScan("com.liren.third")
//@SpringBootApplication
//public class BeanTheoryApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(BeanTheoryApplication.class, args);
//    }
//}


/**
 * 方式二：使用@Import导入类
 */
//@Import(MyConfig.class)
//@SpringBootApplication
//public class BeanTheoryApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(BeanTheoryApplication.class, args);
//    }
//}


/**
 * 方式三：使用@Import导入实现了ImportSelector的实现类
 */
//@Import(MyImportSelector.class)
//@SpringBootApplication
//public class BeanTheoryApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(BeanTheoryApplication.class, args);
//    }
//}


/**
 * 方式四：使用注解，本质还是使用@Import
 */
@ConditionalOnMissingBean
@EnableMyConfig
@SpringBootApplication
public class BeanTheoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeanTheoryApplication.class, args);
    }
}