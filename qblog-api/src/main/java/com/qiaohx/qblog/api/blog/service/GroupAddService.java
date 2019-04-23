package com.qiaohx.qblog.api.blog.service;

import com.qiaohx.qblog.api.blog.vo.GroupAddRequestVo;
import com.qiaohx.util.response.BaseDataResponse;

/**
 * 添加分组
 */
public interface GroupAddService {

    /**
     * 添加分组
     * @param groupAddRequestVo 添加请求参数
     * @return 结果
     * @throws Exception 异常
     */
    BaseDataResponse addGroup(GroupAddRequestVo groupAddRequestVo) throws Exception;
}
