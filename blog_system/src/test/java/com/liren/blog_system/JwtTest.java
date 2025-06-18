package com.liren.blog_system;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testJwt() {
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", 12);
        claim.put("name", "lirendada");

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String encode = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(encode);
        String str = Jwts.builder()
                .setClaims(claim)
                .signWith(SignatureAlgorithm.HS512, encode)
                .compact(); // 👈 核心！将 header + payload + signature 拼接、压缩、编码成一个标准的 JWT 字符串。
        System.out.println(str);


        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
//        JwtParser jwtParser = Jwts.parserBuilder().build();
        System.out.println(jwtParser.parse(str).getBody());
    }
}
