package com.qiaohx.qblog.api.user.vo;

import java.io.Serializable;

/**
 * 登陆请求
 */
public class LoginRequestVo implements Serializable {

    private String certType;
    private String loginCert;
    private String password;

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getLoginCert() {
        return loginCert;
    }

    public void setLoginCert(String loginCert) {
        this.loginCert = loginCert;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
