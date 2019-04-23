package com.qiaohx.qblog.service.blog.model;

import java.io.Serializable;
import java.util.Date;

public class BlogGroupInfo implements Serializable {
    private String groupId;

    private String groupName;

    private String blogId;

    private String flag;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId == null ? null : blogId.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"groupId\":\"")
                .append(groupId).append('\"');
        sb.append(",\"groupName\":\"")
                .append(groupName).append('\"');
        sb.append(",\"blogId\":\"")
                .append(blogId).append('\"');
        sb.append(",\"flag\":\"")
                .append(flag).append('\"');
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"updateDate\":\"")
                .append(updateDate).append('\"');
        sb.append('}');
        return sb.toString();
    }
}