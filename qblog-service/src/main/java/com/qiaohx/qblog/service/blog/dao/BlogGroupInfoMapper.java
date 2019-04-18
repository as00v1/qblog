package com.qiaohx.qblog.service.blog.dao;

import com.qiaohx.qblog.service.blog.model.BlogGroupInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogGroupInfoMapper {
    int insert(BlogGroupInfo record);

    int insertSelective(BlogGroupInfo record);
}