package com.qiaohx.qblog.service.blog.dao;

import com.qiaohx.qblog.service.blog.model.BlogInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogInfoMapper {
    int deleteByPrimaryKey(String blogId);

    int insert(BlogInfo record);

    int insertSelective(BlogInfo record);

    BlogInfo selectByPrimaryKey(String blogId);

    int updateByPrimaryKeySelective(BlogInfo record);

    int updateByPrimaryKey(BlogInfo record);
}