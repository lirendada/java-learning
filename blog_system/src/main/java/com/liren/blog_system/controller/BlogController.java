package com.liren.blog_system.controller;

import com.liren.blog_system.model.response.BlogResponse;
import com.liren.blog_system.model.response.Result;
import com.liren.blog_system.service.BlogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {
    // 使用父类接口声明，然后注入子类类型，这样子利用多态，可以实现解耦合
    @Resource(name = "blogServiceImpl") // 注意开头是小写
    private BlogService blogService;

    @RequestMapping("/getList")
    public List<BlogResponse> getList() {
        log.info("获取博客列表..");
        return blogService.getList();
    }
}
