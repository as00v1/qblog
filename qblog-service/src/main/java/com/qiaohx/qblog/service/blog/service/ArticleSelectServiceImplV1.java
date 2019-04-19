package com.qiaohx.qblog.service.blog.service;

import com.qiaohx.qblog.api.blog.service.ArticleSelectService;
import com.qiaohx.qblog.api.blog.vo.ArticleSelectResponseVo;
import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.api.common.redis.RedisService;
import com.qiaohx.qblog.service.blog.dao.BlogArticleInfoMapper;
import com.qiaohx.qblog.service.blog.model.BlogArticleInfo;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("articleSelectServiceV1")
public class ArticleSelectServiceImplV1 extends AbstractBaseService implements ArticleSelectService {

    @Autowired
    private BlogArticleInfoMapper articleInfoMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 查询文章信息<br>
     * 初级的缓存重建策略：<br>
     *  1.查询缓存，存在返回<br>
     *  2.查询数据库，存在放入缓存并返回；不存在返错误
     * @param articleId 文章ID
     * @return 文章信息
     * @throws Exception 异常
     */
    @Override
    public ArticleSelectResponseVo selectArticleInfo(String articleId) throws Exception {
        BlogArticleInfo blogArticleInfo = (BlogArticleInfo) redisService.get(articleId);
        if (blogArticleInfo != null){
            return ResponseUtil.success(ArticleSelectResponseVo.class);
        }
        logger.info(String.format("文章 %s 缓存失效，重建缓存", articleId));

        blogArticleInfo = articleInfoMapper.selectByPrimaryKey(articleId);
        ArticleSelectResponseVo articleSelectResponseVo = null;
        if (blogArticleInfo != null){// 从数据库中取到值
            logger.info(String.format("读取数据库取到结果"));
            redisService.set(articleId, blogArticleInfo);// 放入缓存
            articleSelectResponseVo = ResponseUtil.success(ArticleSelectResponseVo.class);
            BeanUtils.copyProperties(blogArticleInfo, articleSelectResponseVo);// 拷贝属性
        }else {
            articleSelectResponseVo = ResponseUtil.result(ErrorCodeEnums.ARTICLE_NONE, ArticleSelectResponseVo.class);
        }
        return articleSelectResponseVo;
    }

}
