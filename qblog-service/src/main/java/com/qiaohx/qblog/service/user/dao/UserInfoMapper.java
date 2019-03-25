package com.qiaohx.qblog.service.user.dao;

import com.qiaohx.qblog.service.user.model.UserInfo;

public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);
}