package com.qiaohx.qblog.service.blog.dao;

import com.qiaohx.qblog.service.blog.model.BlogGroupInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogGroupInfoMapper {
    int deleteByPrimaryKey(String groupId);

    int insert(BlogGroupInfo record);

    int insertSelective(BlogGroupInfo record);

    BlogGroupInfo selectByPrimaryKey(String groupId);

    int updateByPrimaryKeySelective(BlogGroupInfo record);

    int updateByPrimaryKey(BlogGroupInfo record);

    List<BlogGroupInfo> queryByBlogId(String blogId);
}