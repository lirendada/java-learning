package com.liren.blog_system.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum ResultStatusEnum {
    SUCCESS(200),
    FAIL(-1);

    @Getter @Setter
    public Integer code;
}
