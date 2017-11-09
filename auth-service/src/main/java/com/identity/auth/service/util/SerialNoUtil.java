package com.identity.auth.service.util;

import lombok.extern.slf4j.Slf4j;


/**
 * 流水号获取工具
 * Created by lijing on 2017/10/26 0026.
 */
@Slf4j
public class SerialNoUtil {


    /**
     * 获取序列号(14位时间+6位ip+6位随机数)
     * @return 序列号
     */
    public static String getSerialNo(){
        String time = DateUtil.getDateTime();
        String roundNum = ("000000"+Math.round(Math.random()*1000000));
        String ip = getBottomHalfIp();
        roundNum = roundNum.substring(roundNum.length()-6,roundNum.length());
        return time+ip+roundNum;
    }

    /**
     * 获取处理后的下半段ip地址
     * @return 处理后的ip地址
     */
    private static String getBottomHalfIp(){
        String ip = IpUtil.getLocalIp();
        String[] ips = ip.split(".");
        if(ips.length>=4){
            return getLastThree(ips[2])+getLastThree(ips[3]);
        }
        return "000000";
    }

    /**
     * 截取字符串末三位 补足三位前补0
     * @param string 需要截取的字符串
     * @return 截取后字符串
     */
    private static String getLastThree(String string){
        String s = "000"+string;
        return s.substring(s.length()-3);
    }
}
