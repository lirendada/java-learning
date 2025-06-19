package com.liren.blog_system;

import com.liren.blog_system.common.utils.JWTUtils;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
public class JwtTest {
    @Test
    public void testJwt() {
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", 12);
        claim.put("name", "lirendada");

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String encode = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(encode);
        String str = Jwts.builder()
                .setClaims(claim)
                .signWith(key)
                .compact(); // 👈 核心！将 header + payload + signature 拼接、压缩、编码成一个标准的 JWT 字符串。
        System.out.println(str);


        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
//        JwtParser jwtParser = Jwts.parserBuilder().build(); // ❌必须要设置签名，才能合法验证通过
        System.out.println(jwtParser.parse(str).getBody());
    }

    @Test
    public void testJwt2() {
        log.info("testJwt2()");
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", 12);
        claim.put("name", "lirendada");

        String jwt = JWTUtils.createJWT(claim);
        System.out.println(jwt);

        System.out.println(JWTUtils.parseJWT(jwt));
    }
}
