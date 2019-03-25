package com.qiaohx.qblog.api.user.service;

import com.qiaohx.qblog.api.user.vo.RegisterRequestVo;
import com.qiaohx.util.response.BaseDataResponse;

/**
 * 注册用户
 */
public interface RegisterService {

    /**
     * 添加注册用户
     * @param registerInfoVo
     * @return
     * @throws Exception
     */
    BaseDataResponse addRegisterUser(RegisterRequestVo registerInfoVo) throws Exception;
}
