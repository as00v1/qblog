package com.qiaohx.qblog.api.blog.vo;

import com.qiaohx.util.response.BaseDataResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel("查询博客文章列表返回参数")
public class ArticleQueryResponseVo extends BaseDataResponse implements Serializable {

    @ApiModelProperty(value = "文章列表", required = true)
    private List<?> list;

    @ApiModelProperty(value = "文章总数", required = true)
    private long count;

    public ArticleQueryResponseVo(int code, String errMsg) {
        super(code, errMsg);
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
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
