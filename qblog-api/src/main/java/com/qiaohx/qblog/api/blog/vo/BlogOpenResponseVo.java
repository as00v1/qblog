package com.qiaohx.qblog.api.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("开通博客返回参数")
public class BlogOpenResponseVo implements Serializable {

    @ApiModelProperty(value = "博客标识", required = true, example = "example")
    private String blogTag;

    public String getBlogTag() {
        return blogTag;
    }

    public void setBlogTag(String blogTag) {
        this.blogTag = blogTag;
    }
}
