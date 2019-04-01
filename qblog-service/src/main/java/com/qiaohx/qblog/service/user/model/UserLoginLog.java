package com.qiaohx.qblog.service.user.model;

import java.io.Serializable;
import java.util.Date;

public class UserLoginLog implements Serializable {
    private String loginLogId;

    private String userId;

    private String certType;

    private String loginCert;

    private String password;

    private String flag;

    private Date loginDate;

    private String ip;

    private static final long serialVersionUID = 1L;

    public String getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(String loginLogId) {
        this.loginLogId = loginLogId == null ? null : loginLogId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    public String getLoginCert() {
        return loginCert;
    }

    public void setLoginCert(String loginCert) {
        this.loginCert = loginCert == null ? null : loginCert.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserLoginLog == {");
        sb.append("\"loginLogId\":\"")
                .append(loginLogId).append('\"');
        sb.append(",\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"certType\":\"")
                .append(certType).append('\"');
        sb.append(",\"loginCert\":\"")
                .append(loginCert).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"flag\":\"")
                .append(flag).append('\"');
        sb.append(",\"loginDate\":\"")
                .append(loginDate).append('\"');
        sb.append(",\"ip\":\"")
                .append(ip).append('\"');
        sb.append('}');
        return sb.toString();
    }
}