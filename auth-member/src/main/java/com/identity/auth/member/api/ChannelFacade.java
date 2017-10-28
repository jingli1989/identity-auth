package com.identity.auth.member.api;

import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.member.model.ChannelInfoResDTO;

import java.util.List;

/**
 * 渠道服务
 * Created by lijing on 2017/10/26 0026.
 */
public interface ChannelFacade {

    /**
     * 获取产品渠道信息
     * @param productId 产品id
     * @param logId 日志id
     * @return 渠道信息
     */
    IdentityAuthResult<List<ChannelInfoResDTO>> channelRoute(String productId,String logId);
}
