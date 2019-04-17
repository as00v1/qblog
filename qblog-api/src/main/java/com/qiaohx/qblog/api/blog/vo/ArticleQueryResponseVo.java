package com.qiaohx.qblog.api.blog.vo;

import com.qiaohx.util.response.BaseDataResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel("查询博客文章列表返回参数")
public class ArticleQueryResponseVo extends BaseDataResponse implements Serializable {

    @ApiModelProperty(value = "文章列表", required = true)
    private List<ArticleInfo> list;

    @ApiModelProperty(value = "文章总数", required = true)
    private int count;

    public ArticleQueryResponseVo(int code, String errMsg) {
        super(code, errMsg);
    }

    public List<ArticleInfo> getList() {
        return list;
    }

    public void setList(List<ArticleInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"list\":")
                .append(list);
        sb.append(",\"count\":")
                .append(count);
        sb.append('}');
        return sb.toString();
    }
}
@ApiModel("文章信息")
class ArticleInfo implements Serializable{

    private String articleId;

    private String blogId;

    private String groupId;

    private String title;

    private String keyWord;

    private String artAbstract;

    private Date createDate;

    private Date updateDate;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
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
}
