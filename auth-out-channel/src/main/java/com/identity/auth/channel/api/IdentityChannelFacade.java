package com.identity.auth.channel.api;

import com.identity.auth.channel.model.IdentityReqDTO;
import com.identity.auth.channel.model.IdentityResDTO;
import com.identity.auth.common.util.IdentityAuthResult;

/**
 * 身份认证渠道
 * Created by lijing on 2017/11/5 0005.
 */
public interface IdentityChannelFacade {
    /**
     * 身份认证
     * @param reqDTO 身份认证请求
     * @return 身份认证响应
     */
    IdentityAuthResult<IdentityResDTO> identityAuth(IdentityReqDTO reqDTO);
}
