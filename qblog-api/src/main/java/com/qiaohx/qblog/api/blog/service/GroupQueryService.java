package com.qiaohx.qblog.api.blog.service;

import com.qiaohx.qblog.api.blog.vo.GroupQueryResponseVo;

/**
 * 查询分组相关
 */
public interface GroupQueryService {

    /**
     * 查询所有分类
     * @param blogId 博客标识
     * @return 所有分类信息
     * @throws Exception 异常
     */
    GroupQueryResponseVo queryGroup(String blogId) throws Exception;
}
