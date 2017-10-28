package com.identity.auth.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * user:LiJing
 * date:2016/9/9.
 */
@Slf4j
public class DateUtil {

    /** YYYYMMDDHHmmSS */
    public static final String YYYYMMDDHHmmSS ="yyyyMMDDHHmmSS";
    /** YYMMDDHHmmSS */
    public static final String YYMMDDHHmmSS ="yyMMDDHHmmSS";

    public static final String YYYY_MM_DD ="yyyy-MM-dd";

    public static Date parse(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            log.error("时间:{},格式化字符串:{},格式化异常", date, pattern);
            return null;
        }
    }

    /**
     * 时间转字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String parseToString(Date date, String pattern) {
            return (new SimpleDateFormat(pattern)).format(date);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(new Date());
    }

    /**
     * 判断 是否为 年份
     * @param year
     * @return
     */
    public static boolean isYear(String year) {
        if (StringUtils.isBlank(year)) {
            return false;
        }
        if(!year.matches("^[0-9]+$")){
            return false;
        }
        if (year.length() > 4) {
            return false;
        }
        return true;
    }

    public static boolean isMonth(String month) {
        if (StringUtils.isBlank(month)) {
            return false;
        }
        if(!month.matches("^[0-9]+$")){
            return false;
        }
        if (Integer.parseInt(month)>12) {
            return false;
        }
        return true;
    }

    /**
     * 日期加减
     * @param date 计算前日期(为空取当前日期)
     * @param days 天数
     * @return 计算后日期
     */
    public static Date addDays(Date date,int days){
        Calendar c = Calendar.getInstance();
        if(date!=null) {
            c.setTime(date);
        }
        c.add(Calendar.DAY_OF_MONTH,days);
        return c.getTime();
    }
}
