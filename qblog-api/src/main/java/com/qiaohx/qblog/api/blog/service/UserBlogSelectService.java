package com.qiaohx.qblog.api.blog.service;

import com.qiaohx.qblog.api.blog.vo.UserBlogRelVo;

public interface UserBlogSelectService {

    /**
     * 根据用户ID查询博客ID
     * @param userId
     * @return
     * @throws Exception
     */
    UserBlogRelVo selectBlogRelByUserId(String userId) throws Exception;
}
