package com.liren.blog_system.common.exception;

import lombok.Data;

@Data
public class BlogException extends RuntimeException {
    private Integer code;
    private String errMsg;

    public BlogException(Integer code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public BlogException(String errMsg) {
        this.errMsg = errMsg;
    }
}
