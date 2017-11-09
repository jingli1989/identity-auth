package com.identity.auth.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 请求头认证信息
 * Created by lijing on 2017/11/4 0004.
 */
@Getter
@Setter
@ToString
public class HeaderAuthDTO {
    /** 验签摘要 */
    private String authCode;
}
