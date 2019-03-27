package com.qiaohx.util.date;

public enum DateFormatRules {
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
    /**
     * yyyyMMdd
     */
    YYYYMMDD("yyyyMMdd"),
    /**
     * yyyyMMddHHmmss
     */
    YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
    /**
     * yyyyMMddHHmmssSSS
     */
    YYYYMMDDHHMMSSSSS("yyyyMMddHHmmssSSS");

    private String format;

    DateFormatRules(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
