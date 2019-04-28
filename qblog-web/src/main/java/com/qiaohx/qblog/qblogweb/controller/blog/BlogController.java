package com.qiaohx.qblog.qblogweb.controller.blog;

import com.qiaohx.qblog.api.blog.service.BlogInfoService;
import com.qiaohx.qblog.api.blog.service.UserBlogSelectService;
import com.qiaohx.qblog.api.blog.vo.BlogInfoVo;
import com.qiaohx.qblog.api.blog.vo.UserBlogRelVo;
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

    private final static String BLOG_BASE_URL = "https://www.qiaohx.com/";
    private final UserBlogSelectService userBlogSelectService;

    private final BlogInfoService blogInfoService;

    @Autowired
    public BlogController(UserBlogSelectService userBlogSelectService, BlogInfoService blogInfoService) {
        this.userBlogSelectService = userBlogSelectService;
        this.blogInfoService = blogInfoService;
    }

    @RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
    public void goBlog(@PathVariable(name="blogId") String blogId,
                       HttpServletRequest request, HttpServletResponse response){
        try {
            BlogInfoVo blogInfoVo = blogInfoService.getBlogInfo(blogId);
            if (blogInfoVo.getCode() != 0){
                logger.info("未查询到博客信息");
                response.sendRedirect(BLOG_BASE_URL + 404);
                return;
            }
            response.sendRedirect(BLOG_BASE_URL + blogId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("重定向到博客异常 " + blogId, e);
            try {
                response.sendRedirect(BLOG_BASE_URL + 500);
            } catch (IOException e1) {
                e1.printStackTrace();
                logger.error("重定向到博客500异常 " + blogId, e);
            }
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
