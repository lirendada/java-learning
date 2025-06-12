package com.liren.book_system.advice;

import com.liren.book_system.model.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public Result handler(Exception e) {
        return Result.fail("内部错误，请联系管理员~");
    }
}
