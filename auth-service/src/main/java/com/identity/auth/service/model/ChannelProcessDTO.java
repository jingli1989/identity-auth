package com.identity.auth.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 渠道处理结果DTO
 * Created by lijing on 2017/11/10 0010.
 */
@Getter
@Setter
@ToString
public class ChannelProcessDTO {
    /** 业务流水 */
    private String businessNo;
    /** 订单号 */
    private String tradeNo;
    /** 计费状态 */
    private String feeFlag;
    /** 认证结果code */
    private String resCode;
    /** 认证结果描述 */
    private String resMsg;
}
