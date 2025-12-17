package com.liren.common.utils;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class JsonUtil {
    /**
     * 将对象转换为json字符串
     */
    public static String toJson(Object obj) {
        return obj == null ? null : JSON.toJSONString(obj);
    }

    /**
     * 将json字符串转化为对象
     */
    public static <T> T toObject(String json_string, Class<T> clazz) {
        if(!StringUtils.hasLength(json_string) || clazz == null) {
            return null;
        } else {
            return JSON.parseObject(json_string, clazz);
        }
    }
}
