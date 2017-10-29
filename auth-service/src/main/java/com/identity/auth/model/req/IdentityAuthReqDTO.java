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
 * 身份认证请求
 * Created by Administrator on 2017/10/29 0029.
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityAuthReqDTO implements Serializable{
    /** 身份证 */
    @JsonProperty("id_card")
    @NotBlank(message = "身份证号码不能为空")
    @Pattern( regexp = RegexUtil.ID_CARD,message = "身份证号码格式有误")
    private String idCard;
    /** 姓名 */
    @JsonProperty("id_name")
    @NotBlank(message = "姓名不能为空")
    @Pattern( regexp = RegexUtil.ID_NAME,message = "姓名格式有误")
    private String idName;
}
