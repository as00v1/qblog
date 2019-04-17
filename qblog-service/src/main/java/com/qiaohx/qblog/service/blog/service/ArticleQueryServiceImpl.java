package com.qiaohx.qblog.service.blog.service;

import com.qiaohx.qblog.api.blog.service.ArticleQueryService;
import com.qiaohx.qblog.api.blog.vo.ArticleQueryRequestVo;
import com.qiaohx.qblog.api.blog.vo.ArticleQueryResponseVo;
import org.springframework.stereotype.Service;

@Service("articleQueryService")
public class ArticleQueryServiceImpl implements ArticleQueryService {
    /**
     * 查询博客文章列表
     *
     * @param articleQueryRequestVo 查询请求
     * @return 查询结果
     * @throws Exception 异常
     */
    @Override
    public ArticleQueryResponseVo queryArticles(ArticleQueryRequestVo articleQueryRequestVo) throws Exception {
        String blogId = articleQueryRequestVo.getBlogId();

        return null;
    }
}
