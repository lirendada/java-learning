package com.liren.blog_system.common.advice;

import com.liren.blog_system.common.exception.BlogException;
import com.liren.blog_system.model.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public Object handler(Exception e) {
        log.error("发生异常：" + e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler
    public Object handler(BlogException e) {
        log.error("发生异常：" + e.getErrMsg());
        return Result.fail(e.getErrMsg());
    }
}
