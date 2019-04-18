package com.qiaohx.qblog.qblogweb;

import com.qiaohx.qblog.api.blog.service.ArticleQueryService;
import com.qiaohx.qblog.api.blog.vo.ArticleQueryRequestVo;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class BlogTest extends QblogWebApplicationTests{

    @Autowired
    ArticleQueryService articleQueryService;

    @org.junit.Test
    public void test(){
        ArticleQueryRequestVo articleQueryRequestVo = new ArticleQueryRequestVo();
        articleQueryRequestVo.setBlogId("1");
        articleQueryRequestVo.setPageNo(1);
        articleQueryRequestVo.setPageSize(2);

        try {
            articleQueryService.queryArticles(articleQueryRequestVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Assert.assertSame("企业数量有误","1","1");
    }
}
