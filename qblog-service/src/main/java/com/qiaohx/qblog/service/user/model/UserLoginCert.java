package com.qiaohx.qblog.service.user.model;

import java.io.Serializable;
import java.util.Date;

public class UserLoginCert implements Serializable {
    private String loginCertId;

    private String userId;

    private String certType;

    private String loginCert;

    private String password;

    private String flag;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public String getLoginCertId() {
        return loginCertId;
    }

    public void setLoginCertId(String loginCertId) {
        this.loginCertId = loginCertId == null ? null : loginCertId.trim();
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}