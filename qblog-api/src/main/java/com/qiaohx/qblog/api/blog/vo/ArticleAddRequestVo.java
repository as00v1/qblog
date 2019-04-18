package com.qiaohx.qblog.api.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("新增文章请求参数")
public class ArticleAddRequestVo implements Serializable {

    @ApiModelProperty(value = "用户标识" ,required = true)
    private String cid;

    @ApiModelProperty(value = "分组ID" ,required = true)
    private String groupId;

    @ApiModelProperty(value = "文章标题" ,required = true)
    private String title;

    @ApiModelProperty(value = "文章关键词")
    private String keyWord;

    @ApiModelProperty(value = "文章描述")
    private String artAbstract;

    @ApiModelProperty(value = "文章内容", required = true)
    private String content;

    @ApiModelProperty(hidden = true)
    private String blogId;

    private static final long serialVersionUID = 1L;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getArtAbstract() {
        return artAbstract;
    }

    public void setArtAbstract(String artAbstract) {
        this.artAbstract = artAbstract;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        sb.append("\"cid\":\"")
                .append(cid).append('\"');
        sb.append(",\"groupId\":\"")
                .append(groupId).append('\"');
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"keyWord\":\"")
                .append(keyWord).append('\"');
        sb.append(",\"artAbstract\":\"")
                .append(artAbstract).append('\"');
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append(",\"blogId\":\"")
                .append(blogId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
