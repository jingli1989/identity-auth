package com.identity.auth.channel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 身份认证请求
 * Created by lijing on 2017/11/9 0009.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class IdentityReqDTO extends BaseChannelReqDTO {
    /** 身份证 */
    private String idCard;
    /** 姓名 */
    private String idName;
}
