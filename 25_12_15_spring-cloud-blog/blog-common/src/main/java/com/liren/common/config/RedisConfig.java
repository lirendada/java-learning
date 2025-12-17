package com.liren.common.config;

import com.liren.common.utils.RedisUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

    @Bean
    @ConditionalOnProperty(
            prefix = "spring.data.redis",
            name = {"host", "port"},      // 两个关键配置都必须存在
            matchIfMissing = false        // 配置缺失时不加载
    )
    public RedisUtil redisUtil(StringRedisTemplate stringRedisTemplate) {
        return new RedisUtil(stringRedisTemplate);
    }
}
