package com.qiaohx.qblog.service.blog.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qiaohx.qblog.api.blog.service.ArticleQueryService;
import com.qiaohx.qblog.api.blog.vo.ArticleQueryRequestVo;
import com.qiaohx.qblog.api.blog.vo.ArticleQueryResponseVo;
import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.service.blog.dao.BlogArticleInfoMapper;
import com.qiaohx.qblog.service.blog.model.BlogArticleInfo;
import com.qiaohx.util.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleQueryService")
public class ArticleQueryServiceImpl extends AbstractBaseService implements ArticleQueryService {

    @Autowired
    private BlogArticleInfoMapper blogArticleInfoMapper;

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
        int pageNo = articleQueryRequestVo.getPageNo();
        int pageSize = articleQueryRequestVo.getPageSize();

        BlogArticleInfo blogArticleInfo = new BlogArticleInfo();
        blogArticleInfo.setBlogId(blogId);
        Page page = PageHelper.startPage(pageNo, pageSize, true);
        List<BlogArticleInfo> blogArticleInfoList = blogArticleInfoMapper.queryByBlogId(blogArticleInfo);
        logger.info(page.getTotal() + "");
        logger.info(blogArticleInfoList.toString());

        ArticleQueryResponseVo articleQueryResponseVo = ResponseUtil.success(ArticleQueryResponseVo.class);
        articleQueryResponseVo.setCount(page.getTotal());
        articleQueryResponseVo.setList(blogArticleInfoList);

        return articleQueryResponseVo;
    }
}
