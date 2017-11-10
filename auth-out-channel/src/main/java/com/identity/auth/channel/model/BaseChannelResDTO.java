package com.identity.auth.channel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 渠道基本响应
 * Created by lijing on 2017/11/9 0009.
 */
@Getter
@Setter
@ToString
public class BaseChannelResDTO implements Serializable{
    /** 渠道id */
    private String channelId;
    /** 渠道请求流水号 */
    private String channelReqNo;
    /** 渠道响应流水号 */
    private String channelResNo;
    /** 渠道处理耗时 (ms)*/
    private Integer channelTime;
    /** 渠道收费标识 */
    private Boolean channelFee;
    /** 渠道响应code */
    private String resCode;
    /** 渠道响应描述 */
    private String resDesc;
}
