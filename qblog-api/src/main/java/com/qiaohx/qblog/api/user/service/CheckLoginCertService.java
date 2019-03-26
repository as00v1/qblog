package com.qiaohx.qblog.api.user.service;

import com.qiaohx.qblog.api.user.vo.LoginRequestVo;
import com.qiaohx.util.response.BaseDataResponse;

/**
 * 校验登录名
 */
public interface CheckLoginCertService {

    /**
     * 校验用户名是否存在
     * @param loginRequestVo 用户名
     * @return 成功/失败
     * @throws Exception 异常
     */
    BaseDataResponse checkLoginCert(LoginRequestVo loginRequestVo) throws Exception;
}
