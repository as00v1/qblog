package com.qiaohx.qblog.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 登陆请求
 */
@ApiModel("登录请求参数")
public class LoginRequestVo implements Serializable {

    @ApiModelProperty(value = "账号类型", required = true, allowableValues ="00:用户名,01:邮箱,02:手机号,03:微信", example = "00")
    private String certType;
    @ApiModelProperty(value = "账号", required = true, example = "example")
    private String loginCert;
    @ApiModelProperty(value = "密码(MD5摘要之后)", example = "1a79a4d60de6718e8e5b326e338ae533")
    private String password;
    @ApiModelProperty(hidden = true)
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginRequestVo == {");
        sb.append("\"certType\":\"")
                .append(certType).append('\"');
        sb.append(",\"loginCert\":\"")
                .append(loginCert).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
