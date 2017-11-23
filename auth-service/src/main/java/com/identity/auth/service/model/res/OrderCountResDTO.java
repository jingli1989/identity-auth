package com.identity.auth.service.model.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 订单统计响应
 * Created by lijing on 2017/11/14 0014.
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCountResDTO implements Serializable{
    /** 订单数 */
    @JsonProperty("order_num")
    private Integer orderNum;
    /** 订单金额 */
    @JsonProperty("order_money")
    private Integer orderMoney;
    /** 商户号 */
    @JsonProperty("member_id")
    private String memberId;
    /** 产品id */
    @JsonProperty("product_id")
    private String productId;
    /** 订单状态 */
    @JsonProperty("order_status")
    private String orderStatus;
    /** 计费标识 */
    @JsonProperty("fee_flag")
    private String feeFlag;
    /** 响应code */
    @JsonProperty("res_code")
    private String resCode;
    /** 渠道id */
    @JsonProperty("channel_id")
    private String channelId;
}
