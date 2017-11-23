package com.identity.auth.service.model.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单明细查询响应
 * Created by lijing on 2017/11/14 0014.
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderQueryResDTO implements Serializable{
    /** 编号 */
    @JsonProperty("id")
    private Integer id;

    /** 商户号 */
    @JsonProperty("member_id")
    private String memberId;

    /** 商户订单号 */
    @JsonProperty("member_trans_id")
    private String memberTransId;

    /** 商户订单时间  默认：CURRENT_TIMESTAMP */
    @JsonProperty("member_trans_date")
    private Date memberTransDate;

    /** 产品id */
    @JsonProperty("product_id")
    private String productId;

    /**  订单金额 */
    @JsonProperty("order_money")
    private Integer orderMoney;

    /** 系统流水 */
    @JsonProperty("trade_no")
    private String tradeNo;

    /** 业务流水 */
    @JsonProperty("business_no")
    private String businessNo;

    /** 订单状态(INIT 初始 FAIL 失败 SUCCESS 成功) */
    @JsonProperty("order_status")
    private String orderStatus;

    /** 收费标识 (Y 收费 N 不收费 ) */
    @JsonProperty("fee_flag")
    private String feeFlag;

    /** 响应编码 */
    @JsonProperty("res_code")
    private String resCode;

    /** 响应描述 */
    @JsonProperty("res_msg")
    private String resMsg;

    /** 创建时间  默认：0000-00-00 00:00:00 */
    @JsonProperty("created_at")
    private Date createdAt;

    /** 创建人 */
    @JsonProperty("created_by")
    private String createdBy;

    /** 最后更新时间 */
    @JsonProperty("updated_at")
    private Date updatedAt;

    /** 最后更新人 */
    @JsonProperty("updated_by")
    private String updatedBy;
}
