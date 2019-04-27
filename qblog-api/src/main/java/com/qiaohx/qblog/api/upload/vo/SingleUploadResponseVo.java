package com.qiaohx.qblog.api.upload.vo;

import com.qiaohx.util.response.BaseDataResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("上传文件返回参数")
public class SingleUploadResponseVo extends BaseDataResponse implements Serializable {

    @ApiModelProperty(value = "图片真实地址", required = true)
    private String url;

    public SingleUploadResponseVo(int code, String errMsg) {
        super(code, errMsg);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"url\":\"")
                .append(url).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
