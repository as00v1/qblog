package com.qiaohx.qblog.api.blog.service;

import com.qiaohx.qblog.api.blog.vo.ArticleAddRequestVo;
import com.qiaohx.qblog.api.blog.vo.ArticleAddResponseVo;
import com.qiaohx.qblog.api.blog.vo.ArticleUpdateRequestVo;
import com.qiaohx.util.response.BaseDataResponse;

public interface ArticleUpdateService {

    /**
     * 更新文章
     * @param articleUpdateRequestVo 文章内容
     * @return 成功
     * @throws Exception 异常
     */
    BaseDataResponse updateArticle(ArticleUpdateRequestVo articleUpdateRequestVo) throws Exception;
}
