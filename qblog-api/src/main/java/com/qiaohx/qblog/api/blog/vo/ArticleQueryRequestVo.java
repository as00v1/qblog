package com.qiaohx.qblog.api.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("查询博客文章列表请求参数")
public class ArticleQueryRequestVo implements Serializable {

    @ApiModelProperty(value = "博客标识", required = true, example = "example")
    private String blogId;

    @ApiModelProperty(value = "分组标签", example = "example")
    private String groupId;

    @ApiModelProperty(value = "页码", required = true, example = "1")
    private int pageNo;

    @ApiModelProperty(value = "每页数量", required = true, example = "10")
    private int pageSize;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"blogId\":\"")
                .append(blogId).append('\"');
        sb.append(",\"groupId\":\"")
                .append(groupId).append('\"');
        sb.append(",\"pageNo\":")
                .append(pageNo);
        sb.append(",\"pageSize\":")
                .append(pageSize);
        sb.append('}');
        return sb.toString();
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
