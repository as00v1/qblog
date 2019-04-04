package com.qiaohx.qblog.api.user.vo;

import com.qiaohx.util.response.BaseDataResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 登录返回
 */
@ApiModel("登录结果")
public class LoginResponseVo extends BaseDataResponse implements Serializable {

    public LoginResponseVo(int code, String errMsg){
        super(code, errMsg);
    }

    @ApiModelProperty(value = "用户标识", example = "581d267befe0487ba55c1ecb1fda2491")
    private String cid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginResponseVo == {");
        sb.append("\"cid\":\"")
                .append(cid).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
