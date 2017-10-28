package com.identity.auth.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ip获取工具
 * Created by lijing on 2017/10/26 0026.
 */
public class IpUtil {
    public static String getLocalIp(){
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }
}
