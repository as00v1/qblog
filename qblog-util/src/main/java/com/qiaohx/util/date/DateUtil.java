package com.qiaohx.util.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static Date getNow(){
        return new Date();
    }

    /**
     * 获取当前时间的指定格式
     * @param dateFormatRules
     * @return
     */
    public static String dateToStr(DateFormatRules dateFormatRules){
        return dateToStr(getNow(), dateFormatRules);
    }

    /**
     * 时间转换字符串
     * @param dateFormatRules
     * @return
     */
    public static String dateToStr(Date date, DateFormatRules dateFormatRules){
        if(null == dateFormatRules) dateFormatRules = DateFormatRules.YYYY_MM_DD_HH_MM_SS;
        DateFormat dateFormat = new SimpleDateFormat(dateFormatRules.getFormat());
        return dateFormat.format(date);
    }

}
