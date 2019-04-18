package com.qiaohx.qblog.api.blog.service;

import com.qiaohx.qblog.api.blog.vo.ArticleAddRequestVo;
import com.qiaohx.qblog.api.blog.vo.ArticleAddResponseVo;

public interface ArticleAddService {

    /**
     * 新增文章
     * @param articleAddRequestVo 文章内容
     * @return 文章ID
     * @throws Exception 异常
     */
    ArticleAddResponseVo addArticle(ArticleAddRequestVo articleAddRequestVo) throws Exception;
}
