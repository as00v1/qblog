package com.qiaohx.qblog.api.blog.service;

import com.qiaohx.qblog.api.blog.vo.BlogOpenRequestVo;
import com.qiaohx.qblog.api.blog.vo.BlogOpenResponseVo;

/**
 * 开通博客
 */
public interface BlogOpenService {

    /**
     * 开通博客
     * @param blogOpenRequestVo 开通参数
     * @return 开通结果
     * @throws Exception 异常
     */
    BlogOpenResponseVo openBlog(BlogOpenRequestVo blogOpenRequestVo) throws Exception;
}
