package com.liren.blog_system.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JWTUtils {
    // 没办法直接调用非静态变量secret
    // 所以换个思路，用传参方式来进行初始化
    // 即创建配置类调用init()来进行SECRET_KEY的初始化
    private static Key SECRET_KEY;

    // 由配置类主动调用初始化，对secret进行解码，然后转化为Key类型
    public static void init(String secret) {
        System.out.println("初始化密钥：" + secret);
        SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    /**
     * 根据传入的claims也就是载荷，生成对应的JWT
     */
    public static String createJWT(Map<String, Object> claims) {
        if (SECRET_KEY == null) {
            throw new IllegalStateException("SECRET_KEY 未初始化！");
        }

        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SECRET_KEY)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1小时有效
                .compact(); // 👈 核心！将 header + payload + signature 拼接、压缩、编码成一个标准的 JWT 字符串。
        return jwt;
    }

    /**
     * 将生成JWT字符串解析后进行返回
     */
    public static Claims parseJWT(String jwt) {
        if (SECRET_KEY == null) {
            throw new IllegalStateException("SECRET_KEY 未初始化！");
        }
        if(jwt.isEmpty()) {
            throw new IllegalStateException("JWT参数错误！");
        }

        Claims claim = null;
        try {
            claim = (Claims) Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parse(jwt)
                    .getBody();
        } catch (Exception e) {
            log.error("解析令牌出错，token = {}, 错误 = {}", jwt, e.getMessage());
        }
        return claim; // 有效就返回 claim，失败就返回 null
    }
}
