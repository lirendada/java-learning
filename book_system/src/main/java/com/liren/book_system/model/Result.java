package com.liren.book_system.model;

import com.liren.book_system.enums.ResultStatusEnum;
import lombok.Data;


@Data
public class Result<T> {
    private T data;
    private ResultStatusEnum code;
    private String errMsg;

    public static <T> Result<T> success(T data) {
        Result<T> res = new Result<>();
        res.setCode(ResultStatusEnum.NORMAL);
        res.setData(data);
        res.setErrMsg("");
        return res;
    }

    public static <T> Result<T> unlogin() {
        Result<T> res = new Result<>();
        res.setCode(ResultStatusEnum.UNLOGIN);
        res.setData(null);
        res.setErrMsg("unlogin");
        return res;
    }
}
