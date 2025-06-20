package com.liren.blog_system.controller;

import com.liren.blog_system.model.request.AddBlogRequest;
import com.liren.blog_system.model.request.UpdateBlogRequest;
import com.liren.blog_system.model.response.BlogResponse;
import com.liren.blog_system.service.BlogService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {
    // 使用父类接口声明，然后注入子类类型，这样子利用多态，可以实现解耦合
    @Resource(name = "blogServiceImpl") // 注意开头是小写
    private BlogService blogService;

    @GetMapping("/getList")
    public List<BlogResponse> getList() {
        log.info("获取博客列表..");
        return blogService.getList();
    }

    @RequestMapping("/getBlogDetail")
    public BlogResponse getBlogDetail(@RequestParam("blogId")
                                          @NotNull
                                          @Positive Integer id) {
        log.info("获取博客列表..");
        return blogService.getBlogDetail(id);
    }

    @RequestMapping("/add")
    public Boolean addBlog(@RequestBody @Validated AddBlogRequest req) {
        log.info("addBlog: " + req);
        return blogService.addBlog(req);
    }

    @RequestMapping("/update")
    public Boolean updateBlog(@RequestBody @Validated UpdateBlogRequest req) {
        log.info("updateBlog: " + req);
        return blogService.updateBlog(req);
    }

    @RequestMapping("/delete")
    public Boolean deleteBlog(@RequestParam("blogId") @NotNull Integer id) {
        log.info("deleteBlog: " + id);
        return blogService.deleteBlog(id);
    }
}
