package com.qiaohx.qblog.service.blog.service;

import com.qiaohx.qblog.api.blog.service.UserBlogSelectService;
import com.qiaohx.qblog.api.blog.vo.UserBlogRelVo;
import com.qiaohx.qblog.service.blog.dao.UserBlogRelMapper;
import com.qiaohx.qblog.service.blog.model.UserBlogRel;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userBlogSelectService")
public class UserBlogSelectServiceImpl implements UserBlogSelectService {

    @Autowired
    private UserBlogRelMapper userBlogRelMapper;

    /**
     * 根据用户ID查询博客ID
     *
     * @param userId 用户ID
     * @return 博客关系
     * @throws Exception 异常
     */
    @Override
    public UserBlogRelVo selectBlogRelByUserId(String userId) throws Exception {
        UserBlogRel userBlogRel = userBlogRelMapper.selectByUserId(userId);
        UserBlogRelVo userBlogRelVo = null;
        if (userBlogRel != null){
            userBlogRelVo = ResponseUtil.success(UserBlogRelVo.class);
            BeanUtils.copyProperties(userBlogRel, userBlogRelVo);
        }else {
            userBlogRelVo = ResponseUtil.result(ErrorCodeEnums.BLOG_NONE, UserBlogRelVo.class);
        }
        return userBlogRelVo;
    }
}
