package com.identity.auth.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举
 * Created by lijing on 2017/10/23 0023.
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    /** 系统异常 */
    SYSTEM_ERROR("SYSTEM_ERROR","系统异常"),

    /** 参数异常 */
    PARAMS_ERROR("PARAMS_ERROR","请求参数异常"),


    MEMBER_NOT_EXITS("MEMBER_NOT_EXITS","商户不存在或状态为禁用"),
    MEMBER_IS_EXITS("MEMBER_IS_EXITS","商户已存在"),
    MEMBER_PRODUCT_IS_EXITS("MEMBER_PRODUCT_IS_EXITS","商户产品已开通"),
    MEMBER_PRODUCT_NOT_EXITS("MEMBER_PRODUCT_NOT_EXITS","商户产品未开通"),


    PRODUCT_NOT_EXITS("PRODUCT_NOT_EXITS","产品不存在或状态为禁用"),
    PRODUCT_IS_EXITS("PRODUCT_IS_EXITS","产品已存在"),

    CHANNEL_NOT_EXITS("CHANNEL_NOT_EXITS","渠道不存在或状态为禁用"),
    CHANNEL_IS_EXITS("CHANNEL_IS_EXITS","渠道已存在"),
    PRODUCT_CHANNEL_NOT_EXITS("PRODUCT_CHANNEL_NOT_EXITS","产品渠道不存在或状态为禁用"),
    PRODUCT_CHANNEL_IS_EXITS("PRODUCT_CHANNEL_IS_EXITS","产品渠道已存在"),

    TRANS_ID_IS_EXITS("TRANS_ID_IS_EXITS","商户订单号已存在"),

    /** 数据库异常 */
    DB_DATA_IS_EXITS("DB_DATA_IS_EXITS","数据已经存在"),
    DB_DATA_NOT_EXITS("DB_DATA_NOT_EXITS","数据不存在"),


    ;
    /** 错误码 */
    private String code;
    /** 错误描述 */
    private String msg;
}
