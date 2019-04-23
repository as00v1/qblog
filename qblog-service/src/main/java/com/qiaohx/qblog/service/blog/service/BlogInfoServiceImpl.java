package com.qiaohx.qblog.service.blog.service;

import com.qiaohx.qblog.api.blog.service.BlogInfoService;
import com.qiaohx.qblog.api.blog.vo.BlogInfoVo;
import com.qiaohx.qblog.service.blog.dao.BlogInfoMapper;
import com.qiaohx.qblog.service.blog.model.BlogInfo;
import com.qiaohx.util.constant.BaseConstant;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blogInfoService")
public class BlogInfoServiceImpl implements BlogInfoService {

    @Autowired
    private BlogInfoMapper blogInfoMapper;

    /**
     * 获取博客信息
     *
     * @param blogId 博客ID
     * @return 博客信息
     * @throws Exception 异常
     */
    @Override
    public BlogInfoVo getBlogInfo(String blogId) throws Exception {
        BlogInfo blogInfo = blogInfoMapper.selectByPrimaryKey(blogId);
        if (blogInfo == null){
            return ResponseUtil.result(ErrorCodeEnums.BLOG_NONE, BlogInfoVo.class);
        }
        if (!BaseConstant.FLAG_1.equals(blogInfo.getFlag())){
            return ResponseUtil.result(ErrorCodeEnums.BLOG_NONE, BlogInfoVo.class);
        }
        BlogInfoVo blogInfoVo = ResponseUtil.success(BlogInfoVo.class);
        BeanUtils.copyProperties(blogInfo, blogInfoVo);
        return blogInfoVo;
    }
}
