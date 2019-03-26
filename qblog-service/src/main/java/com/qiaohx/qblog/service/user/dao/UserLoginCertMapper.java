package com.qiaohx.qblog.service.user.dao;

import com.qiaohx.qblog.service.user.model.UserLoginCert;

public interface UserLoginCertMapper {
    int deleteByPrimaryKey(String loginCertId);

    int insert(UserLoginCert record);

    int insertSelective(UserLoginCert record);

    UserLoginCert selectByPrimaryKey(String loginCertId);

    int updateByPrimaryKeySelective(UserLoginCert record);

    int updateByPrimaryKey(UserLoginCert record);
}