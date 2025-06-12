package com.liren.book_system.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liren.book_system.model.Result;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 作用：判断当前返回值是否要被该 Advice 处理。返回 true 则会执行 beforeBodyWrite。
     * @param returnType 当前控制器方法的返回类型（带泛型信息）
     * @param converterType 当前将要使用的消息转换器类型
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true; // 表示启用beforeBodyWrite
    }

    /**
     * 作用：当 supports() 返回 true 时，这个方法就会执行，用于对 Controller 返回的数据 body 做最后处理
     *      比如修改数据、添加统一响应包装等等
     * @param body the body to be written
     * @param returnType 当前控制器方法的返回类型（带泛型信息）
     * @param selectedContentType 当前请求的响应类型
     * @param selectedConverterType 实际使用的消息转换器类型
     * @param request 封装的 HTTP 请求信息
     * @param response 封装的 HTTP 响应对象，可设置响应头等
     */
    @Override
    @SneakyThrows
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 已经是Result类型就直接返回
        if (body instanceof Result) {
            return body;
        }
        // 返回字符串类型时特殊处理，防止类型不匹配
        if (body instanceof String) {
            return objectMapper.writeValueAsString(Result.success(body));
        }

        return Result.success(body);
    }
}
