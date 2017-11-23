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
 * 渠道订单查询
 * Created by lijing on 2017/11/15 0015.
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelOrderQueryReqDTO implements Serializable{

    /** 系统流水 */
    @JsonProperty("trade_no")
    private String tradeNo;

    /** 渠道编号 */
    @JsonProperty("channel_id")
    @Pattern(regexp = RegexUtil.CHANNEL_ID, message = "渠道号格式错误")
    private String channelId;

    /** 渠道计费标识(Y 收费 N 不收费) */
    @JsonProperty("fee_flag")
    @Pattern(regexp = RegexUtil.FEE_FLAG, message = "收费标识错误，只支持(Y|N)")
    private String feeFlag;

    /** 渠道耗时(ms) */
    @JsonProperty("channel_time")
    private Integer channelTime;

    /** 渠道响应代码 */
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

}
