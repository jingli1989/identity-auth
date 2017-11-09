package com.identity.auth.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否计费标识
 * Created by lijing on 2017/10/26 0026.
 */
@Getter
@AllArgsConstructor
public enum FeeFlagEnum {
    Y("Y","计费"),
    N("N","不计费"),
    ;
    /** 计费标识 */
    private String code;
    /** 描述 */
    private String desc;
}
