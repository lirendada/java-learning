package com.liren.book_system.interceptor;

import com.liren.book_system.constant.Constants;
import com.liren.book_system.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        HttpSession session = request.getSession(false);
        if(!checkLogin(session)) {
            // 如果未登录，设置状态码、错误信息等然后返回false
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            String errMsg = "用户未登录";
            response.getOutputStream().write(errMsg.getBytes("UTF-8"));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle...");
    }

    private boolean checkLogin(HttpSession session) {
        if(session != null) {
            UserInfo user = (UserInfo)session.getAttribute(Constants.SESSION_USER_KEY);
            if(user != null && user.getId() > 0) {
                return true;
            }
        }
        return false; // 到这说明未登录
    }
}
