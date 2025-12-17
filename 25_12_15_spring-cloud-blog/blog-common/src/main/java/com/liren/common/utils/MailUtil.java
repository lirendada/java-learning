package com.liren.common.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

public class MailUtil {
    private JavaMailSender javaMailSender;
    private MailProperties mailProperties;

    public MailUtil(JavaMailSender javaMailSender, MailProperties mailProperties) {
        this.javaMailSender = javaMailSender;
        this.mailProperties = mailProperties;
    }

    public void sendMail(String to, String subject, String content) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);
        helper.setFrom(mailProperties.getUsername(), mailProperties.getProperties().getOrDefault("personal", mailProperties.getUsername()));
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        javaMailSender.send(message);
    }
}
