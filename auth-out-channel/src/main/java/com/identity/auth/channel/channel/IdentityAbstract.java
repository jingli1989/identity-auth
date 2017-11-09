package com.identity.auth.channel.channel;

import com.identity.auth.channel.model.IdentityReqDTO;
import com.identity.auth.channel.model.IdentityResDTO;

/**
 * 身份证认证抽象类
 * Created by lijing on 2017/11/9 0009.
 */
public abstract class IdentityAbstract {

    /**
     * 认证
     * @param reqDTO 认证请求
     * @return 认证响应
     */
    public abstract IdentityResDTO identityAuth(IdentityReqDTO reqDTO);

    /**
     * 获取渠道id
     * @return 渠道id
     */
    public abstract String getChannelId();
}
