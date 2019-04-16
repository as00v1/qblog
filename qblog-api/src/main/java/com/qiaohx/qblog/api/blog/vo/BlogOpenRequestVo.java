package com.qiaohx.qblog.api.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("开通博客请求参数")
public class BlogOpenRequestVo implements Serializable {

    @ApiModelProperty(value = "用户ID", required = true, example = "example")
    private String userId;
    @ApiModelProperty(value = "博客名称", required = true, example = "example")
    private String blogName;
    @ApiModelProperty(value = "博客简介", example = "example")
    private String introduce;

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"blogName\":\"")
                .append(blogName).append('\"');
        sb.append(",\"introduce\":\"")
                .append(introduce).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
