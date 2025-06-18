package com.liren.blog_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liren.blog_system.common.constants.Constants;
import com.liren.blog_system.common.utils.BeanTransUtils;
import com.liren.blog_system.mapper.BlogInfoMapper;
import com.liren.blog_system.model.BlogInfo;
import com.liren.blog_system.model.response.BlogResponse;
import com.liren.blog_system.service.BlogService;
import lombok.extern.slf4j.Slf4j;
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
}
