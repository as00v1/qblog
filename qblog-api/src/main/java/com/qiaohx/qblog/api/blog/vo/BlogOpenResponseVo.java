package com.qiaohx.qblog.api.blog.vo;

import com.qiaohx.util.response.BaseDataResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("开通博客返回参数")
public class BlogOpenResponseVo extends BaseDataResponse implements Serializable {

    @ApiModelProperty(value = "博客标识", required = true, example = "example")
    private String blogTag;

    public BlogOpenResponseVo(int code, String errMsg) {
        super(code, errMsg);
    }

    public String getBlogTag() {
        return blogTag;
    }

    public void setBlogTag(String blogTag) {
        this.blogTag = blogTag;
    }
}
