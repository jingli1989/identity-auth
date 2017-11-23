package com.identity.auth.service.model.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.identity.auth.common.util.RegexUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 订单统计查询请求
 * Created by lijing on 2017/11/14 0014.
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCountReqDTO implements Serializable{
    /** 商户号 */
    @JsonProperty("member_id")
    @Pattern(regexp = RegexUtil.MEMBER_ID, message = "商户号格式错误")
    private String memberId;
    /** 产品id */
    @JsonProperty("product_id")
    @Pattern(regexp = RegexUtil.PRODUCT_ID, message = "产品编号格式错误")
    private String productId;
    /** 订单状态(INIT 初始 FAIL 失败 SUCCESS 成功) */
    @JsonProperty("order_status")
    @Pattern(regexp = RegexUtil.ORDER_STATUS, message = "订单状态错误，只支持(INIT|SUCCESS|FAIL)")
    private String orderStatus;
    /** 收费标识 (Y 收费 N 不收费 ) */
    @JsonProperty("fee_flag")
    @Pattern(regexp = RegexUtil.FEE_FLAG, message = "收费标识错误，只支持(Y|N)")
    private String feeFlag;
    /** 响应编码 */
    @JsonProperty("res_code")
    private String resCode;
    /** 查询开始时间 */
    @JsonProperty("start_time")
    @Pattern(regexp = RegexUtil.DATE_YYYYMMDDHH24MMSS, message = "查询开始日期格式错误(yyyyMMddHHmmss)")
    private String startTime;
    /** 查询结束时间 */
    @JsonProperty("end_time")
    @Pattern(regexp = RegexUtil.DATE_YYYYMMDDHH24MMSS, message = "查询结束日期格式错误(yyyyMMddHHmmss)")
    private String endTime;
    /** 渠道id */
    @JsonProperty("channel_id")
    @Pattern(regexp = RegexUtil.CHANNEL_ID, message = "渠道id格式错误")
    private String channelId;
}
