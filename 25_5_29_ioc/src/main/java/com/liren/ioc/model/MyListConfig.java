package com.liren.ioc.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties("mylist")
public class MyListConfig {
    private List<String> name;
    private List<String> key;
}
