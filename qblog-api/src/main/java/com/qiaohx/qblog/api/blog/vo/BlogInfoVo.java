package com.qiaohx.qblog.api.blog.vo;

import com.qiaohx.util.response.BaseDataResponse;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;
@ApiModel("博客信息")
public class BlogInfoVo extends BaseDataResponse implements Serializable {
    private String blogId;

    private String bolgName;

    private String introduce;

    private Integer level;

    private Date createDate;

    private String blogTag;

    private static final long serialVersionUID = 1L;

    public BlogInfoVo(int code, String errMsg) {
        super(code, errMsg);
    }

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