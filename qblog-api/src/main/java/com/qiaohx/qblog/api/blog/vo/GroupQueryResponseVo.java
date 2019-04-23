package com.qiaohx.qblog.api.blog.vo;

import com.qiaohx.util.response.BaseDataResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel("分类查询返回参数")
public class GroupQueryResponseVo  extends BaseDataResponse implements Serializable {


    public GroupQueryResponseVo(int code, String errMsg) {
        super(code, errMsg);
    }

    @ApiModelProperty(value = "分类列表", required = true)
    private List<?> list;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"list\":")
                .append(list);
        sb.append('}');
        return sb.toString();
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

}
