package com.qiaohx.qblog.service.user.service;

import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.api.user.service.LoginService;
import com.qiaohx.qblog.api.user.vo.LoginRequestVo;
import com.qiaohx.qblog.api.user.vo.LoginResponseVo;
import com.qiaohx.qblog.service.common.sequence.SequenceUtil;
import com.qiaohx.qblog.service.user.dao.UserInfoMapper;
import com.qiaohx.qblog.service.user.dao.UserLoginCertMapper;
import com.qiaohx.qblog.service.user.dao.UserLoginLogMapper;
import com.qiaohx.qblog.service.user.model.UserInfo;
import com.qiaohx.qblog.service.user.model.UserLoginCert;
import com.qiaohx.qblog.service.user.model.UserLoginLog;
import com.qiaohx.util.constant.BaseConstant;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service("loginService")
public class LoginServiceImpl extends AbstractBaseService implements LoginService {

    @Autowired
    private UserLoginCertMapper userLoginCertMapper;
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 登录
     *
     * @param loginRequestVo 登录请求参数
     * @return 返回登录信息
     * @throws Exception 错误
     */
    @Override
    public LoginResponseVo login(LoginRequestVo loginRequestVo) throws Exception {
        UserLoginCert userLoginCert = new UserLoginCert();
        userLoginCert.setCertType(loginRequestVo.getCertType());
        userLoginCert.setLoginCert(loginRequestVo.getLoginCert());
//        userLoginCert.setPassword(loginRequestVo.getPassword());
        userLoginCert = userLoginCertMapper.selectByLoginCert(userLoginCert);
        if (userLoginCert == null){// 找不到此用户
            return ResponseUtil.result(ErrorCodeEnums.LOGIN_CERT_FAIL, LoginResponseVo.class);
        }
        // 用户存在，比较密码
        String password = userLoginCert.getPassword();
        boolean flag = false;
        if (password.equals(loginRequestVo.getPassword())) {
            logger.info("登陆成功");
            flag = true;
        }else {
            logger.info("密码错误，登陆失败");
        }
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setLoginLogId(SequenceUtil.getSequence());
        userLoginLog.setUserId(userLoginCert.getUserId());
        userLoginLog.setCertType(userLoginCert.getCertType());
        userLoginLog.setLoginCert(userLoginCert.getLoginCert());
        userLoginLog.setPassword(loginRequestVo.getPassword());
        userLoginLog.setFlag(flag ? BaseConstant.FLAG_1 : BaseConstant.FLAG_0);// 记录登录成功or失败
        userLoginLog.setLoginDate(new Date());
        userLoginLog.setIp(loginRequestVo.getIp());
        userLoginLogMapper.insertSelective(userLoginLog);

        if (!flag){
            return ResponseUtil.result(ErrorCodeEnums.PASSWORD_FAIL, LoginResponseVo.class);
        }

        // 用户名密码无误，检查用户信息
        String userId = userLoginCert.getUserId();
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if (userInfo == null){
            logger.error("登陆成功，但是用户不存在！");
            return ResponseUtil.result(ErrorCodeEnums.PASSWORD_FAIL,"用户不存在！如有疑问请联系管理员！", LoginResponseVo.class);
        }

        String userFlag = userInfo.getFlag();
        if (BaseConstant.FLAG_0.equals(userFlag)){
            logger.error("登陆成功，但是用户已被删除！");
            return ResponseUtil.result(ErrorCodeEnums.PASSWORD_FAIL,"用户不存在！如有疑问请联系管理员！", LoginResponseVo.class);
        }else if (BaseConstant.FLAG_2.equals(userFlag)){
            logger.error("登陆成功，但是用户已被冻结！");
            return ResponseUtil.result(ErrorCodeEnums.USER_FREEZE, LoginResponseVo.class);
        }

        String cid = userInfo.getCid();
        if (null == cid || "".equals(cid)){
            logger.info(String.format("%s 的cid为空", userId));
            userInfo.setCid(UUID.randomUUID().toString().replaceAll("-", ""));
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
        }
        LoginResponseVo loginResponseVo = ResponseUtil.success(LoginResponseVo.class);
        loginResponseVo.setCid(cid);

        return loginResponseVo;
    }
}
