package com.qiaohx.qblog.api.blog.service;

import com.qiaohx.qblog.api.blog.vo.ArticleSelectResponseVo;

public interface ArticleSelectService {

    /**
     * 查询文章信息
     * @param articleId 文章ID
     * @return 文章信息
     * @throws Exception 异常
     */
    ArticleSelectResponseVo selectArticleInfo(String articleId) throws Exception;
}
