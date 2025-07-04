package com.liren.blog_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.liren.blog_system.common.constants.Constants;
import com.liren.blog_system.common.utils.BeanTransUtils;
import com.liren.blog_system.mapper.BlogInfoMapper;
import com.liren.blog_system.model.BlogInfo;
import com.liren.blog_system.model.request.AddBlogRequest;
import com.liren.blog_system.model.request.UpdateBlogRequest;
import com.liren.blog_system.model.response.BlogResponse;
import com.liren.blog_system.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Override
    public List<BlogResponse> getList() {
        LambdaQueryWrapper<BlogInfo> qw = new LambdaQueryWrapper<BlogInfo>()
                .eq(BlogInfo::getDeleteFlag, Constants.BLOG_NORMAL);
        List<BlogInfo> blogInfos = blogInfoMapper.selectList(qw);

        // 使用map()转化为BlogResponse类型
        List<BlogResponse> blogResponses = blogInfos.stream()
                .map(blogInfo -> BeanTransUtils.trans(blogInfo))
                .collect(Collectors.toList());
        return blogResponses;
    }

    @Override
    public BlogResponse getBlogDetail(Integer id) {
        LambdaQueryWrapper<BlogInfo> qw = new LambdaQueryWrapper<BlogInfo>()
                .eq(BlogInfo::getId, id)
                .eq(BlogInfo::getDeleteFlag, Constants.BLOG_NORMAL);
        BlogInfo blogInfo = blogInfoMapper.selectOne(qw);
        return BeanTransUtils.trans(blogInfo);
    }

    @Override
    public Boolean addBlog(AddBlogRequest req) {
        BlogInfo blogInfo = new BlogInfo();
        BeanUtils.copyProperties(req, blogInfo);
        Integer ret = null;
        try {
            ret = blogInfoMapper.insert(blogInfo);
            if(ret == 1) {
                return true;
            }
        } catch (Exception e) {
            log.error("发布博客失败:" + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean updateBlog(UpdateBlogRequest req) {
        LambdaUpdateWrapper<BlogInfo> qw = new LambdaUpdateWrapper<>();
        qw.set(BlogInfo::getTitle, req.getTitle())
                .set(BlogInfo::getContent, req.getContent())
                .eq(BlogInfo::getId, req.getId())
                .eq(BlogInfo::getDeleteFlag, Constants.BLOG_NORMAL);
        Integer ret = null;
        try {
            ret = blogInfoMapper.update(qw);
            if(ret == 1) {
                return true;
            }
        } catch (Exception e) {
            log.error("更新博客失败:" + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean deleteBlog(Integer id) {
        LambdaUpdateWrapper<BlogInfo> qw = new LambdaUpdateWrapper<>();
        qw.set(BlogInfo::getDeleteFlag, Constants.BLOG_DELETE)
                .eq(BlogInfo::getId, id);
        Integer ret = null;
        try {
            ret = blogInfoMapper.update(qw);
            if(ret == 1) {
                return true;
            }
        } catch (Exception e) {
            log.error("删除博客失败:" + e.getMessage());
        }
        return false;
    }
}
