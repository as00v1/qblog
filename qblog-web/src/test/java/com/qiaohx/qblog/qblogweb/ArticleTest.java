package com.qiaohx.qblog.qblogweb;

import com.qiaohx.qblog.api.blog.service.ArticleSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArticleTest extends QblogWebApplicationTests{

    @Autowired
    @Qualifier("articleSelectServiceV2")
    ArticleSelectService articleSelectService;

    @org.junit.Test
    public void selectArticle() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);
        Runnable runnable = () -> {
            try {
                articleSelectService.selectArticleInfo("20190418161831000010");
                articleSelectService.selectArticleInfo("20190418160119000008");
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 10; i++) {
            System.out.println("设置线程：" + i);
            new Thread(runnable).start();

        }
        countDownLatch.await();


    }
}
