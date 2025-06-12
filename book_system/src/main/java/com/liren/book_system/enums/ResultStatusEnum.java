package com.liren.book_system.enums;

public enum ResultStatusEnum {
    UNLOGIN(1),
    FAIL(2),
    NORMAL(200);

    private Integer code;

    ResultStatusEnum(Integer code) {
        this.code = code;
    }

    public static Integer getCodeByStatus(ResultStatusEnum res) {
        return res.code;
    }
}
