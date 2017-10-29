package com.identity.auth.model.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.identity.auth.common.util.RegexUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 基础请求DTO
 * Created by lijing on 2017/10/28 0028.
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseReqDTO implements Serializable{
    /** 商户号 */
    @JsonProperty("member_id")
    @NotBlank(message = "商户号不能为空")
    @Pattern( regexp = RegexUtil.MEMBER_ID,message = "商户号必须大于10位小于32位")
    private String memberId;
    /** 商户订单号 */
    @JsonProperty("member_trans_id")
    @NotBlank(message = "商户请求订单号不能为空")
    @Pattern( regexp = RegexUtil.TRANS_ID,message = "商户请求订单号必须大于10位小于32位")
    private String memberTransId;
    /** 商户订单时间 */
    @JsonProperty("member_trans_date")
    @NotBlank(message = "商户请求交易时间不能为空")
    @Pattern( regexp = RegexUtil.DATE_YYYYMMDDHH24MMSS,message = "商户请求交易时间格式错误，格式必须为[yyyyMMddHHmmss]且为有效时间")
    private String memberTransDate;

}
