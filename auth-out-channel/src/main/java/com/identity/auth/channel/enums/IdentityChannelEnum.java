package com.identity.auth.channel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 渠道信息枚举
 * Created by lijing on 2017/11/9 0009.
 */
@Getter
@AllArgsConstructor
public enum IdentityChannelEnum {
    TEST_CHANNEL("TEST_CHANNEL","测试渠道"),
    ;
    /** 订单状态 */
    private String code;
    /** 描述 */
    private String desc;
}
