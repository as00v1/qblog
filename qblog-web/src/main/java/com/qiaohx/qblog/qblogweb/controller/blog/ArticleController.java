package com.qiaohx.qblog.qblogweb.controller.blog;

import com.qiaohx.qblog.api.blog.service.ArticleAddService;
import com.qiaohx.qblog.api.blog.service.ArticleQueryService;
import com.qiaohx.qblog.api.blog.service.UserBlogSelectService;
import com.qiaohx.qblog.api.blog.vo.*;
import com.qiaohx.qblog.api.user.service.SelectUserInfoByCidService;
import com.qiaohx.qblog.api.user.vo.LoginRequestVo;
import com.qiaohx.qblog.api.user.vo.UserInfoResponseVo;
import com.qiaohx.util.response.BaseDataResponse;
import com.qiaohx.util.response.ErrorCodeEnums;
import com.qiaohx.util.response.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article")
@Api(description = "博客文章接口")
public class ArticleController {

    @Autowired
    private ArticleQueryService articleQueryService;
    @Autowired
    private ArticleAddService articleAddService;
    @Autowired
    private UserBlogSelectService userBlogSelectService;
    @Autowired
    private SelectUserInfoByCidService selectUserInfoByCidService;

    @ApiOperation(value = "查询博客文章列表", notes = "用于对博客文章的列表分页查询")
    @RequestMapping(value = "/queryArticles", method = RequestMethod.POST)
    public ArticleQueryResponseVo queryArticles(@RequestBody ArticleQueryRequestVo articleQueryRequestVo) throws Exception {
        return articleQueryService.queryArticles(articleQueryRequestVo);
    }

    @ApiOperation(value = "新增文章", notes = "添加文章")
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public ArticleAddResponseVo addArticle(@RequestBody ArticleAddRequestVo articleAddRequestVo) throws Exception {
        String cid = articleAddRequestVo.getCid();
        UserInfoResponseVo userInfoResponseVo = selectUserInfoByCidService.selectUserInfoByCid(cid);
        if (userInfoResponseVo.getCode() != 0){// 用户校验失败
            return new ArticleAddResponseVo(userInfoResponseVo.getCode(), userInfoResponseVo.getErrMsg());
        }
        UserBlogRelVo userBlogRelVo = userBlogSelectService.selectBlogRelByUserId(userInfoResponseVo.getUserId());
        if (userBlogRelVo.getCode() != 0){
            return new ArticleAddResponseVo(userBlogRelVo.getCode(), userBlogRelVo.getErrMsg());
        }
        articleAddRequestVo.setBlogId(userBlogRelVo.getBlogId());
        return articleAddService.addArticle(articleAddRequestVo);
    }
}
