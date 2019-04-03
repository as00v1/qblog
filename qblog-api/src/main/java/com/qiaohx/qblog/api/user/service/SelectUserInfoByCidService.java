package com.qiaohx.qblog.api.user.service;

import com.qiaohx.qblog.api.user.vo.UserInfoResponseVo;

/**
 * 查询用户信息接口
 */
public interface SelectUserInfoByCidService {

    UserInfoResponseVo selectUserInfoByCid(String cid) throws Exception;
}
