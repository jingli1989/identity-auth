package com.identity.auth.member.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 商户信息
 * Created by lijing on 2017/10/26 0026.
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberInfoResDTO implements Serializable{

    /** 商户编号 */
    @JsonProperty("member_id")
    private String memberId;

    /** 商户全称 */
    @JsonProperty("member_name")
    private String memberName;

    /** 商户简称 */
    @JsonProperty("short_name")
    private String shortName;

    /** 商户状态(NORMAL 正常 DISABLE 不可用) */
    @JsonProperty("member_status")
    private String memberStatus;

    /** 商户加密key */
    @JsonProperty("security_key")
    private String securityKey;

    /** 联系人 */
    @JsonProperty("contacts")
    private String contacts;

    /** 座机 */
    @JsonProperty("tel_number")
    private String telNumber;

    /** 联系人手机号码 */
    @JsonProperty("phone_num")
    private String phoneNum;

    /** 公司所在城市 */
    @JsonProperty("city_name")
    private String cityName;

    /** 公司地址 */
    @JsonProperty("company_address")
    private String companyAddr;
}
