package com.qiaohx.qblog.api.blog.service;

import com.qiaohx.qblog.api.blog.vo.BlogInfoVo;

/**
 * 博客信息服务
 */
public interface BlogInfoService {

    /**
     * 获取博客信息
     * @param blogId 博客ID
     * @return 博客信息
     * @throws Exception 异常
     */
    BlogInfoVo getBlogInfo(String blogId) throws Exception;
}
