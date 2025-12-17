package com.liren.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

@SpringBootTest
public class MailTest {
    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void sendSimpleMail() throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);
        helper.setFrom("2916776007@qq.com", "liren微服务系统");
        helper.setTo("2916776007@qq.com");
        helper.setSubject("测试邮件");
        helper.setText("测试邮件内容");

        javaMailSender.send(message);
    }
}
