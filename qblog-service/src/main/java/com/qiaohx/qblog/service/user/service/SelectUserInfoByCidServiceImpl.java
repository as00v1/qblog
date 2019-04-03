package com.qiaohx.qblog.service.user.service;

import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.api.user.service.SelectUserInfoByCidService;
import com.qiaohx.qblog.api.user.vo.UserInfoResponseVo;
import com.qiaohx.qblog.service.user.dao.UserInfoMapper;
import com.qiaohx.qblog.service.user.model.UserInfo;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("selectUserInfoByCidService")
public class SelectUserInfoByCidServiceImpl extends AbstractBaseService implements SelectUserInfoByCidService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfoResponseVo selectUserInfoByCid(String cid) throws Exception {
        UserInfo userInfo = userInfoMapper.selectByCid(cid);
        if (userInfo == null){
            logger.warn("用户cid失效");
            return ResponseUtil.result(ErrorCodeEnums.LOGIN_ERROR, UserInfoResponseVo.class);
        }
        String flag = userInfo.getFlag();
        if (!"1".equals(flag)){
            logger.warn(String.format("用户 %s 状态为 %s ，禁止访问", userInfo.getUserId(), flag));
            return ResponseUtil.result(ErrorCodeEnums.USER_FREEZE, UserInfoResponseVo.class);
        }
        UserInfoResponseVo userInfoResponseVo = new UserInfoResponseVo();
        BeanUtils.copyProperties(userInfo, userInfoResponseVo);
        return userInfoResponseVo;
    }
}
