package com.identity.auth.channel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 基本渠道请求
 * Created by lijing on 2017/11/5 0005.
 */
@Getter
@Setter
@ToString
public class BaseChannelReqDTO implements Serializable{
    /** 渠道id */
    private String channelId;
    /** 渠道请求流水号 */
    private String channelReqNo;

}
