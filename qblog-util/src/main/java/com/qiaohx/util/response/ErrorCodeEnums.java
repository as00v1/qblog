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
    LOGIN_FAIL(1000, "登录失效，请重新登录！"),// 1000-1999留作登录
    RE_CERT(1001, "用户名重复！"),
    /**
     * 参数问题
     */
    PARAM_EMPTY(2000, "请检查必传参数！"),// 2000-2999参数问题
    PARAM_ERROR(2001, "请检查参数！"),
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
