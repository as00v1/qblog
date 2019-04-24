package com.qiaohx.qblog.service.blog.service;

import com.qiaohx.qblog.api.blog.service.ArticleSelectService;
import com.qiaohx.qblog.api.blog.vo.ArticleSelectResponseVo;
import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.api.common.redis.RedisService;
import com.qiaohx.qblog.service.blog.dao.BlogArticleInfoMapper;
import com.qiaohx.qblog.service.blog.model.BlogArticleInfo;
import com.qiaohx.util.constant.BaseConstant;
import com.qiaohx.util.response.BaseDataResponse;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("articleSelectServiceV3")
public class ArticleSelectServiceImplV3 extends AbstractBaseService implements ArticleSelectService {

    @Autowired
    private BlogArticleInfoMapper articleInfoMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 查询文章信息 V3版<br>
     * 初级的缓存重建策略：<br>
     *  1.查询缓存，存在返回<br>
     *  2.阻塞其他线程，先补偿查询缓存，如果没有结果就查询DB重建缓存
     *  3.如果发生缓存穿透，记录穿透值放入redis，禁止两分钟
     * @param articleId 文章ID
     * @return 文章信息
     * @throws Exception 异常
     */
    @Override
    public ArticleSelectResponseVo selectArticleInfo(String articleId) throws Exception {
        ArticleSelectResponseVo articleSelectResponseVo;
        String nullKey = "IS_NULL_" + articleId;// 是否为空key
        String isNull = (String)redisService.get(nullKey);
        if (isNull != null){
            logger.warn("此文章在2min内穿透过，禁止访问");
            return ResponseUtil.result(ErrorCodeEnums.ARTICLE_NONE, ArticleSelectResponseVo.class);
        }
        BlogArticleInfo blogArticleInfo = (BlogArticleInfo) redisService.get(articleId);
        if (blogArticleInfo != null){// 缓存有值
            logger.info(String.format("文章 %s 缓存有效", articleId));
            articleSelectResponseVo = ResponseUtil.success(ArticleSelectResponseVo.class);
        }else {
            logger.info(String.format("文章 %s 缓存失效，重建缓存", articleId));
            synchronized (ArticleSelectServiceImplV3.class){
                blogArticleInfo = (BlogArticleInfo) redisService.get(articleId);
                if (blogArticleInfo != null){
                    logger.info(String.format("文章 %s 缓存有效(阻塞)", articleId));
                    articleSelectResponseVo = ResponseUtil.success(ArticleSelectResponseVo.class);
                }else {
                    blogArticleInfo = articleInfoMapper.selectByPrimaryKey(articleId);
                    if (blogArticleInfo != null) {// 从数据库中取到值
                        logger.info("读取数据库取到结果");
                        redisService.set(articleId, blogArticleInfo);// 放入缓存
                        articleSelectResponseVo = ResponseUtil.success(ArticleSelectResponseVo.class);
                    }else {
                        logger.warn("文章缓存穿透，开始2min的防备");
                        redisService.set(nullKey, "true", 60 * 2);// 放一个两分钟的失效key，防止穿透
                        return ResponseUtil.result(ErrorCodeEnums.ARTICLE_NONE, ArticleSelectResponseVo.class);
                    }
                }
            }
        }
        BeanUtils.copyProperties(blogArticleInfo, articleSelectResponseVo);// 拷贝属性
        return articleSelectResponseVo;
    }

}
