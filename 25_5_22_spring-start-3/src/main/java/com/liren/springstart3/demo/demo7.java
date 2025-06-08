package com.liren.springstart3.demo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/demo7")
public class demo7 {
    @RequestMapping("/d1")
    public String getcookie1(HttpServletRequest req) {
        // 获取请求中的所有cookie，使用数组的时候记得判断是否为空
        Cookie[] cookies = req.getCookies();
        System.out.println(cookies.length);
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        return "返回cookies成功！";
    }

    @RequestMapping("/d2")
    public String getcookie2(@CookieValue("liren") String liren,
                             @CookieValue("asd") String asd) {
        return "liren: " + liren + "<br>asd: " + asd;
    }

    @RequestMapping("/setsession")
    public String setSession(HttpServletRequest req) {
        // 先获取session对象，设置为false，则不存在session就返回null，用于判断是否过期
        HttpSession session = req.getSession(false);
        if(session == null) {
            // 过期了，要创建新的session对象
            System.out.println("超时了！");
            session = req.getSession(true); // 这次获取session对象用true，则不存在会创建
            session.setMaxInactiveInterval(5); // 设置会话过期时间，单位为秒
        }
        session.setAttribute("liren", "lirendada_love_yt");
        return "session设置成功";
    }

    /**
     * 方式一：使用HttpServletRequest类的getSession()
     */
    @RequestMapping("/g1")
    public String getsession1(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(); // 默认为true，不存在会创建新session
        if(session != null && session.getAttribute("liren") != null) {
            return "liren: " + session.getAttribute("liren");
        }
        return "null";
    }

    /**
     * 方式二：直接在参数列表中获取HttpSession对象
     */
    @RequestMapping("/g2")
    public String getsession2(HttpSession session) {
        if(session != null && session.getAttribute("liren") != null) {
            return "liren: " + session.getAttribute("liren");
        }
        return "null";
    }

    /**
     * 方式三：使用注解
     */
    @RequestMapping("/g3")
    public String getsession3(@SessionAttribute String liren) {
        return "liren: " + liren;
    }
}
