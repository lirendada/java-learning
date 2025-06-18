package com.liren.blog_system.common.utils;

import com.liren.blog_system.model.BlogInfo;
import com.liren.blog_system.model.response.BlogResponse;
import org.springframework.beans.BeanUtils;

public class BeanTransUtils {
    /**
     * 将BlogInfo转化为BlogResponse，注意对应要转换的字段名要相同
     * @param blogInfo
     * @return
     */
    public static BlogResponse trans(BlogInfo blogInfo) {
        BlogResponse response = new BlogResponse();
        BeanUtils.copyProperties(blogInfo, response);
        return response;
    }
}
