package com.qiaohx.qblog.service.blog.dao;

import com.qiaohx.qblog.service.blog.model.BlogArticleInfo;

public interface BlogArticleInfoMapper {
    int deleteByPrimaryKey(String articleId);

    int insert(BlogArticleInfo record);

    int insertSelective(BlogArticleInfo record);

    BlogArticleInfo selectByPrimaryKey(String articleId);

    int updateByPrimaryKeySelective(BlogArticleInfo record);

    int updateByPrimaryKeyWithBLOBs(BlogArticleInfo record);

    int updateByPrimaryKey(BlogArticleInfo record);
}