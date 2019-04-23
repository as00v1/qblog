package com.qiaohx.qblog.qblogweb.controller.blog;

import com.qiaohx.qblog.api.blog.service.UserBlogSelectService;
import com.qiaohx.qblog.api.blog.vo.ArticleAddResponseVo;
import com.qiaohx.qblog.api.blog.vo.UserBlogRelVo;
import com.qiaohx.qblog.api.user.vo.UserInfoResponseVo;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final static Logger logger = LoggerFactory.getLogger(BlogController.class);

    private final UserBlogSelectService userBlogSelectService;

    @Autowired
    public BlogController(UserBlogSelectService userBlogSelectService) {
        this.userBlogSelectService = userBlogSelectService;
    }

    @RequestMapping("/{blogTag}")
    public void goBlog(@PathVariable(name="blogTag") String blogTag,
                       HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect("https://www.qiaohx.com");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("重定向到博客异常 " + blogTag, e);
        }
    }

    @ApiOperation(value = "用户-博客关系查询接口")
    @RequestMapping(value = "/selectUserBlogRel", method = RequestMethod.GET)
    @ResponseBody
    public UserBlogRelVo selectUserBlogRel(String userId) throws Exception{
        UserBlogRelVo userBlogRelVo = userBlogSelectService.selectBlogRelByUserId(userId);
        if (userBlogRelVo.getCode() != 0){
            return ResponseUtil.result(ErrorCodeEnums.BLOG_NONE, UserBlogRelVo.class);
        }
        return userBlogRelVo;
    }
}
