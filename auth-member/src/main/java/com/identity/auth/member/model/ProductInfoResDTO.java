package com.identity.auth.member.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 产品响应信息
 * Created by lijing on 2017/10/26 0026.
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfoResDTO implements Serializable{
    /** 商户号 */
    @JsonProperty("member_id")
    private String memberId;
    /** 产品编号 */
    @JsonProperty("product_id")
    private String productId;

    /** 产品状态(NORMAL 正常 DISABLE 禁用 ) */
    @JsonProperty("product_status")
    private String productStatus;

    /** 产品名称 */
    @JsonProperty("product_name")
    private String productName;

    /** 产品单价(单位:分) */
    @JsonProperty("unit_price")
    private Integer unitPrice;
}
