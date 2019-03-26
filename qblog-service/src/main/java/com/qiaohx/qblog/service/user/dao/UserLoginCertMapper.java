package com.qiaohx.qblog.service.user.dao;

import com.qiaohx.qblog.api.user.vo.LoginRequestVo;
import com.qiaohx.qblog.service.user.model.UserLoginCert;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginCertMapper {
    int deleteByPrimaryKey(String loginCertId);

    int insert(UserLoginCert record);

    int insertSelective(UserLoginCert record);

    UserLoginCert selectByPrimaryKey(String loginCertId);

    int updateByPrimaryKeySelective(UserLoginCert record);

    int updateByPrimaryKey(UserLoginCert record);

    /**
     * 根据登录凭证查找用户
     * @param record
     * @return
     */
    UserLoginCert selectByLoginCert(LoginRequestVo record);
}