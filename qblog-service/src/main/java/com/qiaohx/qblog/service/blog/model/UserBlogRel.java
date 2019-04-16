package com.qiaohx.qblog.service.blog.model;

import java.io.Serializable;
import java.util.Date;

public class UserBlogRel implements Serializable {
    private String relId;

    private String userId;

    private String blogId;

    private String flag;

    private Date createDate;

    private static final long serialVersionUID = 1L;

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId == null ? null : relId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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
}