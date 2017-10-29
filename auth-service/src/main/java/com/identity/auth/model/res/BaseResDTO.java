package com.identity.auth.model.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 基础响应DTO
 * Created by lijing on 2017/10/28 0028.
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResDTO implements Serializable{
    /** 商户号 */
    @JsonProperty("member_id")
    private String memberId;
    /** 商户订单号 */
    @JsonProperty("member_trans_id")
    private String memberTransId;
    /** 商户订单时间 */
    @JsonProperty("member_trans_date")
    private String memberTransDate;
    /** 响应流水号 */
    @JsonProperty("trade_no")
    private String tradeNo;
    /** 是否收费标识 */
    @JsonProperty("fee_flag")
    private String feeFlag;
    /** 订单金额 */
    @JsonProperty("order_money")
    private String orderMoney;
    /** 响应code */
    @JsonProperty("res_code")
    private String resCode;
    /** 响应描述 */
    @JsonProperty("res_msg")
    private String resMsg;
}
