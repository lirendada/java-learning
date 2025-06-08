package com.liren.ioc.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
class Server {
    private String name;
    private String ip;
}

@Data
@Component
@ConfigurationProperties("myapp")
public class MyApp {
    private String name;                  // 普通字段
    private List<String> tags;            // List
    private Map<String, String> metadata; // Map
    private List<Server> servers;         // 嵌套自定义对象
}
