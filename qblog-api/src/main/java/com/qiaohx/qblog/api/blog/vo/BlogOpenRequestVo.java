package com.qiaohx.qblog.api.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("开通博客请求参数")
public class BlogOpenRequestVo implements Serializable {

    @ApiModelProperty(value = "用户ID", required = true, example = "example")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
