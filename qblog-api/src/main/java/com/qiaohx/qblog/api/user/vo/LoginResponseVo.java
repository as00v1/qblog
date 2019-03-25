package com.qiaohx.qblog.api.user.vo;

import com.qiaohx.util.response.BaseDataResponse;

import java.io.Serializable;

/**
 * 登录返回
 */
public class LoginResponseVo extends BaseDataResponse implements Serializable {

    public LoginResponseVo(int code, String errMsg){
        super(code, errMsg);
    }

    private String cid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
