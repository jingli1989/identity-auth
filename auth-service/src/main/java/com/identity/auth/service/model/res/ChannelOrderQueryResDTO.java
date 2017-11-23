package com.identity.auth.service.model.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 渠道订单查询结果
 * Created by lijing on 2017/11/15 0015.
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelOrderQueryResDTO implements Serializable{

    /** 编号 */
    @JsonProperty("id")
    private Integer id;

    /** 业务流水 */
    @JsonProperty("business_no")
    private String businessNo;

    /** 系统流水 */
    @JsonProperty("trade_no")
    private String tradeNo;

    /** 渠道编号 */
    @JsonProperty("channel_id")
    private String channelId;

    /** 渠道计费标识(Y 收费 N 不收费) */
    @JsonProperty("fee_flag")
    private String feeFlag;

    /** 渠道耗时(ms) */
    @JsonProperty("channel_time")
    private Integer channelTime;

    /** 渠道请求流水 */
    @JsonProperty("req_no")
    private String reqNo;

    /** 渠道响应代码 */
    @JsonProperty("res_code")
    private String resCode;

    /** 渠道响应描述 */
    @JsonProperty("res_msg")
    private String resMsg;

    /** 渠道响应流水 */
    @JsonProperty("res_no")
    private String resNo;

    /** 创建时间  默认：CURRENT_TIMESTAMP */
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
