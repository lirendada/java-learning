package com.liren.gateway.properties;
import java.util.List;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@RefreshScope
@ConfigurationProperties("auth.white")
public class AuthWhiteList {
    private List<String> url; // 白名单配置列表
}
