package com.qiaohx.qblog.service.blog.service;

import com.qiaohx.qblog.api.blog.service.ArticleAddService;
import com.qiaohx.qblog.api.blog.vo.ArticleAddRequestVo;
import com.qiaohx.qblog.api.blog.vo.ArticleAddResponseVo;
import com.qiaohx.qblog.api.common.redis.RedisService;
import com.qiaohx.qblog.service.blog.dao.BlogArticleInfoMapper;
import com.qiaohx.qblog.service.blog.model.BlogArticleInfo;
import com.qiaohx.qblog.service.common.sequence.SequenceUtil;
import com.qiaohx.util.constant.BaseConstant;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("articleAddService")
public class ArticleAddServiceImpl implements ArticleAddService {

    @Autowired
    private BlogArticleInfoMapper blogArticleInfoMapper;
    @Autowired
    private RedisService redisService;

    /**
     * 新增文章
     *
     * @param articleAddRequestVo 文章内容
     * @return 文章ID
     * @throws Exception 异常
     */
    @Override
    public ArticleAddResponseVo addArticle(ArticleAddRequestVo articleAddRequestVo) throws Exception {
        BlogArticleInfo blogArticleInfo = new BlogArticleInfo();
        String articleId = SequenceUtil.getSequence();
        blogArticleInfo.setArticleId(SequenceUtil.getSequence());
        blogArticleInfo.setBlogId(articleAddRequestVo.getBlogId());
        blogArticleInfo.setTitle(articleAddRequestVo.getTitle());
        blogArticleInfo.setGroupId(articleAddRequestVo.getGroupId());
        blogArticleInfo.setContent(articleAddRequestVo.getContent());
        blogArticleInfo.setArtAbstract(articleAddRequestVo.getArtAbstract());
        blogArticleInfo.setKeyWord(articleAddRequestVo.getKeyWord());
        blogArticleInfo.setFlag(BaseConstant.FLAG_1);
        blogArticleInfo.setCreateDate(new Date());
        int row = blogArticleInfoMapper.insertSelective(blogArticleInfo);
        ArticleAddResponseVo articleAddResponseVo = null;
        if (row == 1){
            redisService.set(articleId, blogArticleInfo);
            articleAddResponseVo = ResponseUtil.success(ArticleAddResponseVo.class);
            articleAddResponseVo.setArticleId(articleId);
        }else {
            articleAddResponseVo = ResponseUtil.result(ErrorCodeEnums.UNKNOW_ERROR, "新增文章失败！", ArticleAddResponseVo.class);
        }
        return articleAddResponseVo;
    }
}
