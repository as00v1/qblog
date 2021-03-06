package com.qiaohx.qblog.service.user.service;

import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.api.user.service.CheckLoginCertService;
import com.qiaohx.qblog.api.user.vo.LoginRequestVo;
import com.qiaohx.qblog.service.user.dao.UserLoginCertMapper;
import com.qiaohx.qblog.service.user.model.UserLoginCert;
import com.qiaohx.util.response.BaseDataResponse;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("checkLoginCertService")
public class CheckLoginCertServiceImpl extends AbstractBaseService implements CheckLoginCertService {

//    private final static Logger logger = LoggerFactory.getLogger(CheckLoginCertServiceImpl.class);

    @Autowired
    private UserLoginCertMapper userLoginCertMapper;

    @Override
    public BaseDataResponse checkLoginCert(LoginRequestVo loginRequestVo) {
        logger.info("校验用户信息开始");
        UserLoginCert userLoginCert = new UserLoginCert();
        userLoginCert.setCertType(loginRequestVo.getCertType());
        userLoginCert.setLoginCert(loginRequestVo.getLoginCert());
        userLoginCert.setPassword(loginRequestVo.getPassword());
        userLoginCert = userLoginCertMapper.selectByLoginCert(userLoginCert);
        logger.info("查到结果=" + userLoginCert);
        if (userLoginCert == null)
            return ResponseUtil.success();
        else
            return ResponseUtil.result(ErrorCodeEnums.RE_CERT, BaseDataResponse.class);
    }
}
