package com.liren.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liren.common.pojo.Result;
import com.liren.common.utils.JWTUtils;
import com.liren.gateway.properties.AuthWhiteList;
import io.jsonwebtoken.lang.Collections;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    // 白名单
//    private List<String> whiteList = List.of("/user/login", "/user/register");
    @Autowired
    private AuthWhiteList whiteList;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求和响应
        ServerHttpRequest request = exchange.getRequest();

        // 如果请求路径在白名单中的，直接放行
        List<String> url = whiteList.getUrl();
        if(!Collections.isEmpty(url) && url.contains(request.getURI().getPath())) {
            return chain.filter(exchange);
        }

        // 如果非白名单，则进行身份验证
        String userToken = request.getHeaders().getFirst("user_token");
        log.info("user_token: {}", userToken);
        if(!StringUtils.hasLength(userToken)) {
            return invalidResponse(exchange, "token不存在！");
        }
        Integer userId = JWTUtils.getUserIdFromToken(userToken);
        if(userId == null) {
            return invalidResponse(exchange, "token无效！");
        }
        return chain.filter(exchange);
    }

    @SneakyThrows
    private Mono<Void> invalidResponse(ServerWebExchange exchange, String msg) {
        // 设置响应状态，设置响应类型为json
        log.error("[用户身份认证异常] 请求路径为：{}", exchange.getRequest().getURI().getPath());
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        Result result = Result.fail(msg);
        DataBuffer dataBuffer = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(result));
        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return -200; // 值越小，优先级越高
    }
}
