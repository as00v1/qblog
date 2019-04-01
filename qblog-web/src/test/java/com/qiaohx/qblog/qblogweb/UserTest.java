package com.qiaohx.qblog.qblogweb;

import com.qiaohx.qblog.api.user.service.CheckLoginCertService;
import com.qiaohx.qblog.api.user.service.RegisterService;
import com.qiaohx.qblog.api.user.vo.LoginRequestVo;
import com.qiaohx.qblog.api.user.vo.RegisterRequestVo;
import com.qiaohx.util.response.BaseDataResponse;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTest extends QblogWebApplicationTests{

    @Autowired
    private CheckLoginCertService checkLoginCertService;
    @Autowired
    private RegisterService registerService;

    @org.junit.Test
    public void checkLoginCertServiceTest(){
        LoginRequestVo loginRequestVo = new LoginRequestVo();
        loginRequestVo.setCertType("00");
        loginRequestVo.setLoginCert("1232");
        BaseDataResponse baseDataResponse = checkLoginCertService.checkLoginCert(loginRequestVo);

        Assert.assertSame("返回错误编码",0,baseDataResponse.getCode());
    }

    @org.junit.Test
    public void registerServiceTest(){
        RegisterRequestVo registerInfoVo = new RegisterRequestVo();
        registerInfoVo.setCertType("00");
        registerInfoVo.setLoginCert("ceshi3");
        registerInfoVo.setPassword("md5");
        BaseDataResponse baseDataResponse = null;
        try {
            baseDataResponse = registerService.addRegisterUser(registerInfoVo);
            logger.info(baseDataResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Assert.assertSame("注册失败",0, baseDataResponse.getCode());
    }
}
