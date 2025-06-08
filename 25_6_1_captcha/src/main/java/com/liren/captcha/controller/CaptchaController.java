package com.liren.captcha.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.liren.captcha.model.CaptchaProperties;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@Slf4j
@RequestMapping("/captcha")
@RestController
public class CaptchaController {
    @Autowired
    private CaptchaProperties captchaProp;

    private final static long VALID_MILLIS_TIME = 60*1000; // 60s内合法

    @RequestMapping("/getCaptcha")
    public void getCaptcha(HttpServletResponse resp, HttpSession session) {
        // 设置无缓存机制
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setDateHeader("Expires", 0);

        resp.setContentType("image/jpeg");
        try {
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(captchaProp.getWidth(), captchaProp.getHeight(), 4, 3);
            captcha.write(resp.getOutputStream());

            // 存储验证码以及时间到Session中，方便校验
            String code = captcha.getCode();
            session.setAttribute(captchaProp.getSession().getCode(), code);
            session.setAttribute(captchaProp.getSession().getDate(), new Date());
            log.info("生成的验证码：" + code);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/check")
    public Boolean check(String captcha, HttpSession session) {
        if(!StringUtils.hasLength(captcha)) {
            return false;
        }

        String code = (String)session.getAttribute(captchaProp.getSession().getCode());
        if(!captcha.equalsIgnoreCase(code)) { // 忽略大小写进行比较💥
            return false;
        }
        Date date = (Date)session.getAttribute(captchaProp.getSession().getDate());
        if(date == null || System.currentTimeMillis() - date.getTime() < VALID_MILLIS_TIME) {
            return true;
        }
        return false;
    }
}
