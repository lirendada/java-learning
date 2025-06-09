package com.liren.book_system.enums;

public enum ResultStatusEnum {
    UNLOGIN(1),
    FATAL(2),
    NORMAL(200);

    private Integer code;

    ResultStatusEnum(Integer code) {
        this.code = code;
    }
}
