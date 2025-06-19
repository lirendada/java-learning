package com.liren.blog_system.common.advice;

import com.liren.blog_system.common.exception.BlogException;
import com.liren.blog_system.model.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public Object handler(Exception e) {
        log.error("Exception异常：" + e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler
    public Object handler(BlogException e) {
        log.error("Blog发生异常：" + e.getErrMsg());
        return Result.fail(e.getErrMsg());
    }

    @ExceptionHandler
    public Object handler(MissingServletRequestParameterException e) {
        log.error("参数缺失异常：" + e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler
    public Object handler(NoResourceFoundException e) {
        log.error("未找到资源异常：" + e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler
    public Object handler(IllegalStateException e) {
        log.error("非法状态异常：" + e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler
    public Object handler(MethodArgumentNotValidException e) {
        log.error("参数不合法异常：" + e.getMessage());
        return Result.fail(e.getMessage());
    }

}
