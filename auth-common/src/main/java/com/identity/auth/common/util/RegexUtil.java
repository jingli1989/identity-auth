package com.identity.auth.common.util;

/**
 * 正则表达式
 * Created by Administrator on 2017/10/29 0029.
 */
public class RegexUtil {
    //支持18位及15位身份证 只校验位数及出生日期，不校验地区及生成规则
    public static final String ID_CARD = "([1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2})|([1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx])";
//    中文姓名正则表达式
    public static final String ID_NAME = "[\\u4E00-\\u9FA5A-Za-z]";
//    商户号正则校验
    public static final String MEMBER_ID = "[a-zA-Z0-9]{10,32}";
//    订单号正则校验
    public static final String TRANS_ID = "[a-zA-Z0-9]{10,32}";
//时间正则校验
    public static final String DATE_YYYYMMDDHH24MMSS = "(2[0-9]{3})(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])";


}
