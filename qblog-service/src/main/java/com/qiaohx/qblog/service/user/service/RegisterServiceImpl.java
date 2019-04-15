package com.qiaohx.qblog.service.user.service;

import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.api.user.service.CheckLoginCertService;
import com.qiaohx.qblog.api.user.service.RegisterService;
import com.qiaohx.qblog.api.user.vo.LoginRequestVo;
import com.qiaohx.qblog.api.user.vo.RegisterRequestVo;
import com.qiaohx.qblog.service.common.rabbitmq.RabbitSenderService;
import com.qiaohx.qblog.service.common.sequence.SequenceUtil;
import com.qiaohx.qblog.service.user.dao.UserInfoMapper;
import com.qiaohx.qblog.service.user.dao.UserLoginCertMapper;
import com.qiaohx.qblog.service.user.model.UserInfo;
import com.qiaohx.qblog.service.user.model.UserLoginCert;
import com.qiaohx.util.constant.BaseConstant;
import com.qiaohx.util.constant.MQConstant;
import com.qiaohx.util.response.BaseDataResponse;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service("registerService")
@Transactional(rollbackFor = Exception.class)
public class RegisterServiceImpl extends AbstractBaseService implements RegisterService {

    @Autowired
    private CheckLoginCertService checkLoginCertService;
    @Autowired
    private UserLoginCertMapper userLoginCertMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RabbitSenderService rabbitSenderService;

    /**
     * 添加注册用户
     *
     * @param registerInfoVo
     * @return
     * @throws Exception
     */
    @Override
    public BaseDataResponse addRegisterUser(RegisterRequestVo registerInfoVo) throws Exception {
        // 用户信息
        String userId = SequenceUtil.getSequence();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setFlag(BaseConstant.FLAG_1);
        userInfo.setCreateDate(new Date());
        userInfo.setCid(UUID.randomUUID().toString().replaceAll("-", ""));
        // 登录凭证
        UserLoginCert userLoginCert = new UserLoginCert();
        userLoginCert.setLoginCertId(SequenceUtil.getSequence());
        userLoginCert.setUserId(userId);
        userLoginCert.setCertType(registerInfoVo.getCertType());
        userLoginCert.setLoginCert(registerInfoVo.getLoginCert());
        userLoginCert.setPassword(registerInfoVo.getPassword());
        userLoginCert.setFlag(BaseConstant.FLAG_1);
        userLoginCert.setCreateDate(new Date());
        logger.debug("开始插入用户信息");
        int row = userInfoMapper.insertSelective(userInfo);
        if (row == 1){
            logger.debug("开始插入登录凭证信息");
            row = userLoginCertMapper.insertSelective(userLoginCert);
            if (row == 1){
//                throw new Exception("插入登录凭证失败！");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId", userId);
                rabbitSenderService.send(MQConstant.EXCHANGE_AMQ_DIRECT, MQConstant.ROUTING_KEY_BLOG_OPEN_ROUTING_KEY,
                        jsonObject.toString());

                return ResponseUtil.success();
            }else {
                logger.error("没有插入登录凭证信息！");
                throw new Exception("插入登录凭证失败！");
//                return ResponseUtil.error(ErrorCodeEnums.UNKNOW_ERROR);
            }
        }else {
            logger.error("没有插入用户信息！");
            return ResponseUtil.error(ErrorCodeEnums.UNKNOW_ERROR);
        }
    }
}
