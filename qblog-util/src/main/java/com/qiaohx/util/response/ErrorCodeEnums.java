package com.qiaohx.util.response;

/**
 * 错误编码
 */
public enum ErrorCodeEnums {

    /**
     * 成功
     */
    SUCCESS(0, ResponseUtil.SUCCESS),// 成功
    /**
     * 登录问题
     */
    LOGIN_ERROR(1000, "登录失效，请重新登录！"),// 1000-1999留作登录
    RE_CERT(1001, "用户名重复！"),
    LOGIN_CERT_FAIL(1002, "用户名或密码错误！"),
    PASSWORD_FAIL(1003, "用户名或密码错误！"),
    USER_FREEZE(1004, "此用户已被冻结！"),
    /**
     * 参数问题
     */
    PARAM_EMPTY(2000, "请检查必传参数！"),// 2000-2999参数问题
    PARAM_ERROR(2001, "请检查参数！"),
    SYSTEM_BUSY(8888, "系统繁忙，请稍后再试~"),
    /**
     * 未知异常
     */
    UNKNOW_ERROR(9999, "服务器开小差啦~");

    private Integer code;
    private String message;

    ErrorCodeEnums(Integer code) {
        this.code = code;
    }

    ErrorCodeEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
