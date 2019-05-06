package com.qiaohx.qblog.qblogweb.controller.blog;

import com.qiaohx.qblog.api.blog.service.*;
import com.qiaohx.qblog.api.blog.vo.*;
import com.qiaohx.qblog.api.user.service.SelectUserInfoByCidService;
import com.qiaohx.qblog.api.user.vo.UserInfoResponseVo;
import com.qiaohx.util.response.BaseDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article")
@Api(description = "博客文章接口")
public class ArticleController {

    /**
     * spring建议使用final定义 因为spring bean本来就是单例模式 只会初始化一次<br>
     *     ps. 我觉得没必要定义final，按正常流这个bean不可能被注入两次，异常场景我暂时想不到，可能是为了防止开发手抖？
     */
    private final ArticleQueryService articleQueryService;
    private final ArticleAddService articleAddService;
    private final UserBlogSelectService userBlogSelectService;
    private final SelectUserInfoByCidService selectUserInfoByCidService;
    private final ArticleSelectService articleSelectService;
    private final ArticleUpdateService articleUpdateService;

    /**
     * 使用构造函数进行注入
     * 因为：<br>
     * Java变量的初始化顺序为：
     * 静态变量或静态语句块 –> 实例变量或初始化语句块 –> 构造方法 –> @Autowired
     * @param articleQueryService 文章列表查询
     * @param articleAddService 文章新增
     * @param userBlogSelectService 用户博客查询
     * @param selectUserInfoByCidService 查询用户信息
     * @param articleSelectService 文章详情查询
     * @param articleUpdateService 文章更新
     */
    @Autowired
    public ArticleController(ArticleQueryService articleQueryService,
                             ArticleAddService articleAddService,
                             UserBlogSelectService userBlogSelectService,
                             SelectUserInfoByCidService selectUserInfoByCidService,
                             @Qualifier("articleSelectServiceV3") ArticleSelectService articleSelectService, ArticleUpdateService articleUpdateService) {
        this.articleQueryService = articleQueryService;
        this.articleAddService = articleAddService;
        this.userBlogSelectService = userBlogSelectService;
        this.selectUserInfoByCidService = selectUserInfoByCidService;
        this.articleSelectService = articleSelectService;
        this.articleUpdateService = articleUpdateService;
    }

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

    @ApiOperation(value = "查询博客文章", notes = "用于对博客文章详情查询")
    @RequestMapping(value = "/getArticle", method = RequestMethod.GET)
    public ArticleSelectResponseVo getArticle(String articleId) throws Exception {
        return articleSelectService.selectArticleInfo(articleId);
    }

    @ApiOperation(value = "更新文章", notes = "用于对博客文章更新")
    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    public BaseDataResponse updateArticle(@RequestBody ArticleUpdateRequestVo articleUpdateRequestVo) throws Exception {
        String cid = articleUpdateRequestVo.getCid();
        UserInfoResponseVo userInfoResponseVo = selectUserInfoByCidService.selectUserInfoByCid(cid);
        if (userInfoResponseVo.getCode() != 0){// 用户校验失败
            return new ArticleAddResponseVo(userInfoResponseVo.getCode(), userInfoResponseVo.getErrMsg());
        }
        UserBlogRelVo userBlogRelVo = userBlogSelectService.selectBlogRelByUserId(userInfoResponseVo.getUserId());
        if (userBlogRelVo.getCode() != 0){
            return new ArticleAddResponseVo(userBlogRelVo.getCode(), userBlogRelVo.getErrMsg());
        }
        articleUpdateRequestVo.setBlogId(userBlogRelVo.getBlogId());
        return articleUpdateService.updateArticle(articleUpdateRequestVo);
    }
}
