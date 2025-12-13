package com.liren;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomGatewayFilterFactory.CustomConfig> implements Ordered {
    /**
     * 告诉 Gateway：这个 GatewayFilterFactory 使用哪一种配置对象，如何把 YAML 中的参数绑定进来。
     */
    public CustomGatewayFilterFactory() {
        super(CustomConfig.class);
    }

    @Override
    public GatewayFilter apply(CustomConfig config) {
        /**
         * Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
         *  ServerWebExchange: HTTP请求-响应交互的契约, 提供对HTTP请求和响应的访问, 服务器端请求属性, 请求实例,响应实例等, 类似Context角色
         *  GatewayFilterChain: 过滤器链
         *  Mono: Reactor核心类, 数据流发布者, Mono最多只触发一个事件, 所以可以把Mono 用于在异步任务完成时发出通知.
         *  Mono.fromRunnable: 创建一个包含Runnable元素的数据流
         */
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                log.info("[Pre] Custom Gateway Filter");  // 自定义的 GatewayFilter 逻辑
                return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    log.info("[Post] Custom Gateway Filter");
                }));
            }
        };
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE; // 配置优先级, order越大, 优先级越低
    }

    @Data
    public static class CustomConfig {
        private String name;
    }
}
