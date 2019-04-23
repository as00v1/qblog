package com.qiaohx.qblog.api.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("新增分组请求参数")
public class GroupAddRequestVo implements Serializable {

    @ApiModelProperty(value = "分组名称" ,required = true)
    private String groupName;

    @ApiModelProperty(value = "用户标识" ,required = true)
    private String cid;

    @ApiModelProperty(hidden = true)
    private String blogId;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"groupName\":\"")
                .append(groupName).append('\"');
        sb.append(",\"cid\":\"")
                .append(cid).append('\"');
        sb.append(",\"blogId\":\"")
                .append(blogId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
