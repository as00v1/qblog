package com.qiaohx.qblog.service.user.service;

import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.api.common.redis.RedisService;
import com.qiaohx.qblog.api.user.service.SelectUserInfoByCidService;
import com.qiaohx.qblog.api.user.vo.UserInfoResponseVo;
import com.qiaohx.qblog.service.user.dao.UserInfoMapper;
import com.qiaohx.qblog.service.user.model.UserInfo;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import com.qiaohx.util.serialize.SerializeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("selectUserInfoByCidService")
public class SelectUserInfoByCidServiceImpl extends AbstractBaseService implements SelectUserInfoByCidService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public UserInfoResponseVo selectUserInfoByCid(String cid) throws Exception {

        UserInfo userInfo = (UserInfo) redisService.get(cid, 60*60*24);
//        String userInfoRedis = (String) redisService.get(cid);
        if (null != userInfo){
            logger.debug("redis命中");
        }else {
            logger.debug("redis未命中");
//          认为需要重新登录
//            userInfo = userInfoMapper.selectByCid(cid);
//            if (userInfo == null){
                logger.warn("用户cid失效");
                return ResponseUtil.result(ErrorCodeEnums.LOGIN_ERROR, UserInfoResponseVo.class);
//            }

        }
        String flag = userInfo.getFlag();
        if (!"1".equals(flag)){
            logger.warn(String.format("用户 %s 状态为 %s ，禁止访问", userInfo.getUserId(), flag));
            return ResponseUtil.result(ErrorCodeEnums.USER_FREEZE, UserInfoResponseVo.class);
        }
        UserInfoResponseVo userInfoResponseVo = ResponseUtil.success(UserInfoResponseVo.class);
        BeanUtils.copyProperties(userInfo, userInfoResponseVo);
        return userInfoResponseVo;
    }
}
