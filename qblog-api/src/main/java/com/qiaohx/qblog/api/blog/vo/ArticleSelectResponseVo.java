package com.qiaohx.qblog.api.blog.vo;

import com.qiaohx.util.response.BaseDataResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("查询博客文章信息返回参数")
public class ArticleSelectResponseVo extends BaseDataResponse implements Serializable {

    @ApiModelProperty(value = "文章ID", required = true)
    private String articleId;

    @ApiModelProperty(value = "分类ID", required = true)
    private String groupId;

    @ApiModelProperty(value = "文章标题", required = true)
    private String title;

    @ApiModelProperty(value = "关键字", required = true)
    private String keyWord;

    @ApiModelProperty(value = "文章简介", required = true)
    private String artAbstract;

    @ApiModelProperty(value = "发表时间", required = true)
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "内容")
    private String content;

    private static final long serialVersionUID = 1L;

    public ArticleSelectResponseVo(int code, String errMsg) {
        super(code, errMsg);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"articleId\":\"")
                .append(articleId).append('\"');
        sb.append(",\"groupId\":\"")
                .append(groupId).append('\"');
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"keyWord\":\"")
                .append(keyWord).append('\"');
        sb.append(",\"artAbstract\":\"")
                .append(artAbstract).append('\"');
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"updateDate\":\"")
                .append(updateDate).append('\"');
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
