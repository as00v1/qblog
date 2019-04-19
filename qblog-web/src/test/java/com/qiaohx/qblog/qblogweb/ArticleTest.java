package com.qiaohx.qblog.qblogweb;

import com.qiaohx.qblog.api.blog.service.ArticleSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ArticleTest extends QblogWebApplicationTests{

    @Autowired
    @Qualifier("articleSelectServiceV1")
    ArticleSelectService articleSelectService;

    @org.junit.Test
    public void selectArticle(){

        try {
            articleSelectService.selectArticleInfo("20190418161831000010");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
