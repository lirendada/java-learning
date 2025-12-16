package com.liren.blog.service;


import com.liren.blog.api.pojo.AddBlogInfoRequest;
import com.liren.blog.api.pojo.UpBlogRequest;
import com.liren.blog.api.pojo.BlogInfoResponse;

import java.util.List;

public interface BlogService {
    List<BlogInfoResponse> getList();

    BlogInfoResponse getBlogDeatil(Integer blogId);

    Boolean addBlog(AddBlogInfoRequest addBlogInfoRequest);

    Boolean update(UpBlogRequest upBlogRequest);

    Boolean delete(Integer blogId);
}
