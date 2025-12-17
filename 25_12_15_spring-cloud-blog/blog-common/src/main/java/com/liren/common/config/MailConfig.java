package com.liren.common.config;

import com.liren.common.utils.MailUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {
    @Bean
    @ConditionalOnProperty(prefix = "spring.mail", name = "host")
    public MailUtil mailUtil(JavaMailSender javaMailSender, MailProperties mailProperties) {
        return new MailUtil(javaMailSender, mailProperties);
    }
}
