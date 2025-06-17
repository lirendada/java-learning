package com.liren.blog_system.common.utils;

import com.liren.blog_system.model.BlogInfo;
import com.liren.blog_system.model.response.BlogResponse;
import org.springframework.beans.BeanUtils;

public class BeanTransUtils {
    /**
     * 将BlogInfo转化为BlogResponse
     * @param blogInfo
     * @return
     */
    public static BlogResponse trans(BlogInfo blogInfo) {
        BlogResponse response = new BlogResponse();
        BeanUtils.copyProperties(blogInfo, response);
        return response;
    }
}
