package com.qiaohx.qblog.api.blog.vo;

import com.qiaohx.util.response.BaseDataResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("文章新增返回参数")
public class ArticleAddResponseVo extends BaseDataResponse implements Serializable {

    @ApiModelProperty(value = "文章id", required = true)
    private String articleId;

    public ArticleAddResponseVo(int code, String errMsg) {
        super(code, errMsg);
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"articleId\":\"")
                .append(articleId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
