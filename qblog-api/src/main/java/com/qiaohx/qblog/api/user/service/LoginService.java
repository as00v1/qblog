package com.qiaohx.qblog.api.user.service;

import com.qiaohx.qblog.api.user.vo.LoginRequestVo;
import com.qiaohx.qblog.api.user.vo.LoginResponseVo;

/**
 * 登录接口
 */
public interface LoginService {

    /**
     * 登录
     * @param loginRequestVo 登录请求参数
     * @return 返回登录信息
     * @throws Exception 错误
     */
    LoginResponseVo login(LoginRequestVo loginRequestVo) throws Exception;
}
