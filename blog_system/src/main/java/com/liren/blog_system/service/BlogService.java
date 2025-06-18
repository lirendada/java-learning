package com.liren.blog_system.service;

import com.liren.blog_system.model.response.BlogResponse;
import java.util.List;

public interface BlogService {
    List<BlogResponse> getList();

    BlogResponse getBlogDetail(Integer id);
}
