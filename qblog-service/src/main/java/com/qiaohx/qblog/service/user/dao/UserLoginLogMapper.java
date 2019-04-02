package com.qiaohx.qblog.service.user.dao;

import com.qiaohx.qblog.service.user.model.UserLoginLog;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginLogMapper {
    int deleteByPrimaryKey(String loginLogId);

    int insert(UserLoginLog record);

    int insertSelective(UserLoginLog record);

    UserLoginLog selectByPrimaryKey(String loginLogId);

    int updateByPrimaryKeySelective(UserLoginLog record);

    int updateByPrimaryKey(UserLoginLog record);
}