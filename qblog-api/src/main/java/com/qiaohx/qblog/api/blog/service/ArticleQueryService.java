package com.qiaohx.qblog.api.blog.service;

import com.qiaohx.qblog.api.blog.vo.ArticleQueryRequestVo;
import com.qiaohx.qblog.api.blog.vo.ArticleQueryResponseVo;

public interface ArticleQueryService {

    /**
     * 查询博客文章列表
     * @param articleQueryRequestVo 查询请求
     * @return 查询结果
     * @throws Exception 异常
     */
    ArticleQueryResponseVo queryArticles(ArticleQueryRequestVo articleQueryRequestVo) throws Exception;
}
