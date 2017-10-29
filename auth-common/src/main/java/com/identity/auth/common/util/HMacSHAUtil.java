package com.identity.auth.common.util;

import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 签名摘要工具
 * Created by Administrator on 2017/10/29 0029.
 */
@Slf4j
public class HMacSHAUtil {


    /**
     * HMacSHA256摘要签名
     * @param data 需要摘要原文
     * @param key 摘要密文
     * @return 摘要
     */
    public static String HMacSHA256(String data,String key){
        try {
            byte[] content = data.getBytes("UTF-8");
            byte[] password = key.getBytes("UTF-8");
            return HMacSHA256(content,password);
        } catch (Exception e) {
            log.error("摘要签名失败，原因:{}",e);
            throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    /**
     * HMacSHA256摘要签名校验
     * @param data 原文
     * @param text 密文
     * @param key 密码
     * @return 校验结果 true 一致 false 不一致
     */
    public static boolean HMacSHA256Check(String data,String text,String key){
        try {
            byte[] content = data.getBytes("UTF-8");
            byte[] password = key.getBytes("UTF-8");
            return HMacSHA256(content,password).equals(text.toUpperCase());
        } catch (Exception e) {
            log.error("摘要签名校验失败，原因:{}",e);
            throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }


    /**
     * HMacSHA256摘要签名
     * @param data 需要摘要原文
     * @param key 摘要密文
     * @return 摘要
     */
    private static String HMacSHA256(byte[] data, byte[] key) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        return byte2hex(mac.doFinal(data));

    }

    /**
     * 字节数组转成字符串
     * @param b 字节数组
     * @return 字符串
     */
    private static String byte2hex(byte[] b){
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }
}
