package com.qiaohx.qblog.service.blog.service;

import com.qiaohx.qblog.api.blog.service.ArticleSelectService;
import com.qiaohx.qblog.api.blog.service.ArticleUpdateService;
import com.qiaohx.qblog.api.blog.vo.ArticleSelectResponseVo;
import com.qiaohx.qblog.api.blog.vo.ArticleUpdateRequestVo;
import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.api.common.redis.RedisService;
import com.qiaohx.qblog.service.blog.dao.BlogArticleInfoMapper;
import com.qiaohx.qblog.service.blog.model.BlogArticleInfo;
import com.qiaohx.util.response.BaseDataResponse;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("articleUpdateService")
public class ArticleUpdateServiceImpl extends AbstractBaseService implements ArticleUpdateService {

    @Qualifier("articleSelectServiceV3")
    @Autowired
    private ArticleSelectService articleSelectService;
    @Autowired
    private BlogArticleInfoMapper blogArticleInfoMapper;
    @Autowired
    private RedisService redisService;
    /**
     * 更新文章
     *
     * @param articleUpdateRequestVo 文章内容
     * @return 成功
     * @throws Exception 异常
     */
    @Override
    public BaseDataResponse updateArticle(ArticleUpdateRequestVo articleUpdateRequestVo) throws Exception {
        ArticleSelectResponseVo articleSelectResponseVo = articleSelectService.selectArticleInfo(articleUpdateRequestVo.getArticleId());
        if (articleSelectResponseVo.getCode() != 0){
            return ResponseUtil.result(ErrorCodeEnums.ARTICLE_NONE, BaseDataResponse.class);
        }
        BlogArticleInfo blogArticleInfo = new BlogArticleInfo();
        blogArticleInfo.setArticleId(articleUpdateRequestVo.getArticleId());
        blogArticleInfo.setBlogId(articleUpdateRequestVo.getBlogId());
        blogArticleInfo.setTitle(articleUpdateRequestVo.getTitle());
        blogArticleInfo.setGroupId(articleUpdateRequestVo.getGroupId());
        blogArticleInfo.setContent(articleUpdateRequestVo.getContent());
        blogArticleInfo.setArtAbstract(articleUpdateRequestVo.getArtAbstract());
        blogArticleInfo.setKeyWord(articleUpdateRequestVo.getKeyWord());
        blogArticleInfo.setUpdateDate(new Date());
        int row = blogArticleInfoMapper.updateByPrimaryKeySelective(blogArticleInfo);
        if (row == 1){
            // 更新缓存
            blogArticleInfo.setCreateDate(articleSelectResponseVo.getCreateDate());
            redisService.set(articleUpdateRequestVo.getArticleId(), blogArticleInfo);
            return ResponseUtil.success();
        }else {
            throw new Exception("更新文章失败！");
        }
    }
}
