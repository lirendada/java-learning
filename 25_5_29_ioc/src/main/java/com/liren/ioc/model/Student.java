package com.liren.ioc.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("student") // 名称要对应
@Component // 别忘了要交给spring管理
@Data
public class Student {
    private Integer id;
    private String name;
    private Integer age;
}
