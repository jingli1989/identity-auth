package com.identity.auth.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应码枚举
 * Created by lijing on 2017/11/4 0004.
 */
@Getter
@AllArgsConstructor
public enum ResCodeEnum {
    MATE("0","一致"),
    MIS_MATE("1","不一致"),
    AUTH_ERROR("-1","其它异常"),
    ;
    /** 订单状态 */
    private String code;
    /** 描述 */
    private String desc;
}
