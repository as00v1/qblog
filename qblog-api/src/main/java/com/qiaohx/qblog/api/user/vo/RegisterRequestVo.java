package com.qiaohx.qblog.api.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 注册信息
 */
@ApiModel("注册账号请求参数")
public class RegisterRequestVo implements Serializable {

    @ApiModelProperty(value = "账号类型", required = true, allowableValues ="00:用户名,01:邮箱,02:手机号,03:微信", example = "00")
    private String certType;
    @ApiModelProperty(value = "账号", required = true, example = "example")
    private String loginCert;
    @ApiModelProperty(value = "密码(MD5摘要之后)", example = "1a79a4d60de6718e8e5b326e338ae533")
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RegisterRequestVo == {");
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
