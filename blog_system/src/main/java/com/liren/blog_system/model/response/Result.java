package com.liren.blog_system.model.response;

import com.liren.blog_system.common.enums.ResultStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String errMsg;
    private Object data;

    public static Result success(Object data) {
        return new Result(ResultStatusEnum.SUCCESS.getCode(),
                    null,
                           data);
    }

    public static Result fail(String errMsg) {
        return new Result(ResultStatusEnum.FAIL.getCode(),
                errMsg,
                null);
    }
}
