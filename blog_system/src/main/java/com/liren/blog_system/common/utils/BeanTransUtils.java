package com.liren.blog_system.common.utils;

import com.liren.blog_system.model.BlogInfo;
import com.liren.blog_system.model.UserInfo;
import com.liren.blog_system.model.response.BlogResponse;
import com.liren.blog_system.model.response.LoginResponse;
import com.liren.blog_system.model.response.UserResponse;
import org.springframework.beans.BeanUtils;

public class BeanTransUtils {
    /**
     * 将BlogInfo转化为BlogResponse，注意对应要转换的字段名要相同
     */
    public static BlogResponse trans(BlogInfo blogInfo) {
        BlogResponse response = new BlogResponse();
        BeanUtils.copyProperties(blogInfo, response);
        return response;
    }

    /**
     * 将UserInfo转化为UserResponse，注意对应要转换的字段名要相同
     */
    public static UserResponse trans(UserInfo userInfo) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(userInfo, response);
        return response;
    }
}
