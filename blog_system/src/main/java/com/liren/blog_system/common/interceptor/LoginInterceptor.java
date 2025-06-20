package com.liren.blog_system.common.interceptor;

import com.liren.blog_system.common.constants.Constants;
import com.liren.blog_system.common.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwt = request.getHeader(Constants.USER_TOKEN_NAME); // 从header中获取请求中的token
        if(jwt == null) {
            response.setStatus(401);
            return false;
        }

        Claims claims = JWTUtils.parseJWT(jwt); // 检查 JWT 是否存在、是否合法、是否过期
        if(claims == null) {
            response.setStatus(401);
            return false;
        }
        return true;
    }
}
