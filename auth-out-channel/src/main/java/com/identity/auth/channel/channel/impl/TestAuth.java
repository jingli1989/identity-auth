package com.identity.auth.channel.channel.impl;

import com.identity.auth.channel.channel.IdentityAbstract;
import com.identity.auth.channel.enums.IdentityChannelEnum;
import com.identity.auth.channel.enums.ChannelResCodeEnum;
import com.identity.auth.channel.model.IdentityReqDTO;
import com.identity.auth.channel.model.IdentityResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 测试认证渠道
 * Created by lijing on 2017/11/9 0009.
 */
@Slf4j
@Component
public class TestAuth extends IdentityAbstract {
    /**
     * 认证
     *
     * @param reqDTO 认证请求
     * @return 认证响应
     */
    @Override
    public IdentityResDTO identityAuth(IdentityReqDTO reqDTO) {
        IdentityResDTO resDTO = new IdentityResDTO();
        resDTO.setChannelReqNo(System.currentTimeMillis()+"123456");
        resDTO.setChannelResNo(System.currentTimeMillis()+"654321");
        resDTO.setResCode(ChannelResCodeEnum.MATE.getCode());
        resDTO.setResDesc(ChannelResCodeEnum.MATE.getDesc());
        resDTO.setIdCard(reqDTO.getIdCard());
        resDTO.setIdName(reqDTO.getIdName());
        return resDTO;
    }

    /**
     * 获取渠道id
     *
     * @return 渠道id
     */
    @Override
    public String getChannelId() {
        return IdentityChannelEnum.TEST_CHANNEL.getCode();
    }
}
