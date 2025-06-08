package com.liren.book_system.enums;

public enum BookStatusEnum {
    DELETE(0, "无效"),
    AVAILABLE(1, "可借阅"),
    NOTAVAILABLE(2, "不可借阅");

    Integer code;
    String status;

    BookStatusEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public static String getStatusByCode(Integer code) {
        if(code == 0) {
            return DELETE.status;
        } else if(code == 1) {
            return AVAILABLE.status;
        } else {
            return NOTAVAILABLE.status;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
