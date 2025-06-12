package com.liren.book_system.model;

import com.liren.book_system.enums.ResultStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private T data;
    private Integer code;
    private String errMsg;

    public static <T> Result<T> success(T data) {
        return new Result<>(data,
                ResultStatusEnum.getCodeByStatus(ResultStatusEnum.NORMAL),
                "");
    }

    public static <T> Result<T> unlogin() {
        return new Result<>(null,
                ResultStatusEnum.getCodeByStatus(ResultStatusEnum.UNLOGIN),
                "用户未登录");
    }

    public static <T> Result<T> fail(String errMsg) {
        return new Result<>(null,
                ResultStatusEnum.getCodeByStatus(ResultStatusEnum.FAIL),
                errMsg);
    }
}
