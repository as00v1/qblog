package com.qiaohx.qblog.service.user.service;

import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.api.user.service.CheckLoginCertService;
import com.qiaohx.qblog.api.user.service.RegisterService;
import com.qiaohx.qblog.api.user.vo.LoginRequestVo;
import com.qiaohx.qblog.api.user.vo.RegisterRequestVo;
import com.qiaohx.qblog.service.user.model.UserLoginCert;
import com.qiaohx.util.response.BaseDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("registerService")
@Transactional
public class RegisterServiceImpl extends AbstractBaseService implements RegisterService {

    @Autowired
    private CheckLoginCertService checkLoginCertService;

    /**
     * 添加注册用户
     *
     * @param registerInfoVo
     * @return
     * @throws Exception
     */
    @Override
    public BaseDataResponse addRegisterUser(RegisterRequestVo registerInfoVo) throws Exception {
        LoginRequestVo loginRequestVo = new LoginRequestVo();
        loginRequestVo.setCertType(registerInfoVo.getCertType());
        loginRequestVo.setLoginCert(registerInfoVo.getLoginCert());
        BaseDataResponse baseDataResponse = checkLoginCertService.checkLoginCert(loginRequestVo);
        if (baseDataResponse.getCode() != 0){
            logger.info("登录名校验有问题");
            return baseDataResponse;
        }
        UserLoginCert userLoginCert = new UserLoginCert();
        userLoginCert.setLoginCertId("");
        userLoginCert.setCertType(registerInfoVo.getCertType());
        userLoginCert.setLoginCert(registerInfoVo.getLoginCert());
        userLoginCert.setPassword(registerInfoVo.getPassword());

        return null;
    }
}
