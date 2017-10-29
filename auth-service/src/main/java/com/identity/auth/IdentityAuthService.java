package com.identity.auth;

import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.common.util.LogUtil;
import com.identity.auth.manager.OrderManager;
import com.identity.auth.member.api.ChannelFacade;
import com.identity.auth.member.api.MemberAuthFacade;
import com.identity.auth.member.model.MemberInfoResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 身份认证服务
 * Created by Administrator on 2017/10/29 0029.
 */
@Slf4j
@Service
public class IdentityAuthService {
    /** 订单管理 */
    @Autowired
    private OrderManager orderManager;
    /** 商户认证服务 */
    @Autowired
    private MemberAuthFacade memberAuthFacade;
    /** 渠道信息服务 */
    @Autowired
    private ChannelFacade channelFacade;

    public void authReq(String memberId){
        IdentityAuthResult<MemberInfoResDTO> result = memberAuthFacade.memberCheck(memberId, LogUtil.getLogId());
        if(!result.isSuccess()){

        }
    }


}
