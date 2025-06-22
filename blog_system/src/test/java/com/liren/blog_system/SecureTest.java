package com.liren.blog_system;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class SecureTest {
    @Test
    public void encrypt() {
        String salt = UUID.randomUUID().toString().replace("-", "");
        System.out.println(salt);
        String ciphertext = DigestUtils.md5DigestAsHex((salt + "123456").getBytes(StandardCharsets.UTF_8));
        System.out.println(ciphertext);
    }
}
