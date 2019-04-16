package com.qiaohx.qblog.service.blog.model;

import java.io.Serializable;
import java.util.Date;

public class BlogInfo implements Serializable {
    private String blogId;

    private String bolgName;

    private String introduce;

    private Integer level;

    private String flag;

    private Date createDate;

    private String blogTag;

    private static final long serialVersionUID = 1L;

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId == null ? null : blogId.trim();
    }

    public String getBolgName() {
        return bolgName;
    }

    public void setBolgName(String bolgName) {
        this.bolgName = bolgName == null ? null : bolgName.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public String getBlogTag() {
        return blogTag;
    }

    public void setBlogTag(String blogTag) {
        this.blogTag = blogTag == null ? null : blogTag.trim();
    }
}