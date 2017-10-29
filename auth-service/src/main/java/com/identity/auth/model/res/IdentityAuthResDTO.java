package com.identity.auth.model.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 身份认证响应
 * Created by Administrator on 2017/10/29 0029.
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityAuthResDTO extends BaseResDTO implements Serializable{
    /** 身份证 */
    @JsonProperty("id_card")
    private String idCard;
    /** 姓名 */
    @JsonProperty("id_name")
    private String idName;
}
