package com.identity.auth.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态枚举
 * Created by lijing on 2017/10/26 0026.
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    NORMAL("NORMAL","正常"),
    DISABLE("DISABLE","禁用"),
    ;

    /** 状态 */
    private String code;
    /** 描述 */
    private String desc;
}
