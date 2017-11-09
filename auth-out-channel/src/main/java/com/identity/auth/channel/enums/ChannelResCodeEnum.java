package com.identity.auth.channel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应码枚举
 * Created by lijing on 2017/11/4 0004.
 */
@Getter
@AllArgsConstructor
public enum ChannelResCodeEnum {
    MATE("MATE","一致"),
    MIS_MATE("MIS_MATE","不一致"),
    AUTH_ERROR("AUTH_ERROR","其它异常"),
    ;
    /** 响应代码 */
    private String code;
    /** 描述 */
    private String desc;
}
