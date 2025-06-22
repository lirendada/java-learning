package com.liren.blog_system.common.utils;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class SecureUtils {
    public static String encrypt(String passwd) {
        String salt = UUID.randomUUID().toString().replace("-", "");
        String ret = DigestUtils.md5DigestAsHex((salt + passwd).getBytes(StandardCharsets.UTF_8));
        return salt + ret;
    }

    public static Boolean isValidated(String ciphertext, String passwd) {
        if(!StringUtils.hasLength(passwd) || !StringUtils.hasLength(ciphertext)) {
            return false;
        }
        if(ciphertext.length() != 64) {
            return false;
        }

        String salt = ciphertext.substring(0, 32); // 拿到盐值
        String tmp = DigestUtils.md5DigestAsHex((salt + passwd).getBytes(StandardCharsets.UTF_8));
        return (salt + tmp).equals(ciphertext);
    }
}
