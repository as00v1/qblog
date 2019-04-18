package com.qiaohx.qblog.service.blog.dao;

import com.qiaohx.qblog.service.blog.model.UserBlogRel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBlogRelMapper {
    int deleteByPrimaryKey(String relId);

    int insert(UserBlogRel record);

    int insertSelective(UserBlogRel record);

    UserBlogRel selectByPrimaryKey(String relId);

    int updateByPrimaryKeySelective(UserBlogRel record);

    int updateByPrimaryKey(UserBlogRel record);

    UserBlogRel selectByUserId(String userId);
}