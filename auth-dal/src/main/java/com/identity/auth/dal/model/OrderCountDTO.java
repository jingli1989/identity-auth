package com.identity.auth.dal.model;

import lombok.Data;

/**
 * 订单统计
 * Created by lijing on 2017/11/14 0014.
 */
@Data
public class OrderCountDTO {
    /** 订单数 */
    private Integer orderNum;
    /** 订单金额 */
    private Integer orderMoney;
    /** 商户号 */
    private String memberId;
    /** 产品id */
    private String productId;
    /** 订单状态 */
    private String orderStatus;
    /** 计费标识 */
    private String feeFlag;
    /** 响应code */
    private String resCode;
    /** 渠道id */
    private String channelId;
}
