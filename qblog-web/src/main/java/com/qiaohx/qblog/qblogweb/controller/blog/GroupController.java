package com.qiaohx.qblog.qblogweb.controller.blog;

import com.qiaohx.qblog.api.blog.service.BlogInfoService;
import com.qiaohx.qblog.api.blog.service.GroupAddService;
import com.qiaohx.qblog.api.blog.service.GroupQueryService;
import com.qiaohx.qblog.api.blog.service.UserBlogSelectService;
import com.qiaohx.qblog.api.blog.vo.*;
import com.qiaohx.qblog.api.user.service.SelectUserInfoByCidService;
import com.qiaohx.qblog.api.user.vo.UserInfoResponseVo;
import com.qiaohx.util.response.BaseDataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
@Api(description = "文章分类接口")
public class GroupController {

    private final GroupAddService groupAddService;
    private final GroupQueryService groupQueryService;
    private final SelectUserInfoByCidService selectUserInfoByCidService;
    private final UserBlogSelectService userBlogSelectService;
    private final BlogInfoService blogInfoService;

    @Autowired
    public GroupController(GroupAddService groupAddService, GroupQueryService groupQueryService, SelectUserInfoByCidService selectUserInfoByCidService, UserBlogSelectService userBlogSelectService, BlogInfoService blogInfoService) {
        this.groupAddService = groupAddService;
        this.groupQueryService = groupQueryService;
        this.selectUserInfoByCidService = selectUserInfoByCidService;
        this.userBlogSelectService = userBlogSelectService;
        this.blogInfoService = blogInfoService;
    }

    @ApiOperation(value = "查询博客分类列表", notes = "查询此博客所有文章分类")
    @RequestMapping(value = "/queryGroup", method = RequestMethod.GET)
    public GroupQueryResponseVo queryGroup(String blogId) throws Exception {
        BlogInfoVo blogInfoVo = blogInfoService.getBlogInfo(blogId);
        if (blogInfoVo.getCode() != 0){
            return new GroupQueryResponseVo(blogInfoVo.getCode(), blogInfoVo.getErrMsg());
        }
        return groupQueryService.queryGroup(blogId);
    }

    @ApiOperation(value = "新增文章分类", notes = "添加文章分类")
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public BaseDataResponse addGroup(@RequestBody GroupAddRequestVo groupAddRequestVo) throws Exception {
        String cid = groupAddRequestVo.getCid();
        UserInfoResponseVo userInfoResponseVo = selectUserInfoByCidService.selectUserInfoByCid(cid);
        if (userInfoResponseVo.getCode() != 0){// 用户校验失败
            return new ArticleAddResponseVo(userInfoResponseVo.getCode(), userInfoResponseVo.getErrMsg());
        }
        UserBlogRelVo userBlogRelVo = userBlogSelectService.selectBlogRelByUserId(userInfoResponseVo.getUserId());
        if (userBlogRelVo.getCode() != 0){
            return new ArticleAddResponseVo(userBlogRelVo.getCode(), userBlogRelVo.getErrMsg());
        }
        groupAddRequestVo.setBlogId(userBlogRelVo.getBlogId());
        return groupAddService.addGroup(groupAddRequestVo);
    }
}
