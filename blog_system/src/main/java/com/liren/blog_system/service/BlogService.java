package com.liren.blog_system.service;

import com.liren.blog_system.model.request.AddBlogRequest;
import com.liren.blog_system.model.request.UpdateBlogRequest;
import com.liren.blog_system.model.response.BlogResponse;
import java.util.List;

public interface BlogService {
    List<BlogResponse> getList();

    BlogResponse getBlogDetail(Integer id);

    Boolean addBlog(AddBlogRequest req);

    Boolean updateBlog(UpdateBlogRequest req);

    Boolean deleteBlog(Integer id);
}
