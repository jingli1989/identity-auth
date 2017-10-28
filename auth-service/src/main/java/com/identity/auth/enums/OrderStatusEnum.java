package com.identity.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 * Created by lijing on 2017/10/26 0026.
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    INIT("INIT","初始化"),
    SUCCESS("SUCCESS","成功"),
    FAIL("FAIL","失败"),
    ;
    /** 订单状态 */
    private String code;
    /** 描述 */
    private String desc;
}
