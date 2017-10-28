package com.identity.auth.member.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 渠道信息
 * Created by lijing on 2017/10/26 0026.
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelInfoResDTO implements Serializable{
    /** 渠道编号 */
    @JsonProperty("channel_id")
    private String channelId;

    /** 渠道名称 */
    @JsonProperty("channel_name")
    private String channelName;

    /** 渠道状态(NORMAL 正常 DISABLE 禁用) */
    @JsonProperty("channel_status")
    private String channelStatus;
}
