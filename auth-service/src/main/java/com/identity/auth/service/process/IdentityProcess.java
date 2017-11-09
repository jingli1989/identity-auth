package com.identity.auth.service.process;

import com.identity.auth.channel.api.IdentityChannelFacade;
import com.identity.auth.channel.model.IdentityReqDTO;
import com.identity.auth.channel.model.IdentityResDTO;
import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.dal.model.TBusinessOrderInfo;
import com.identity.auth.member.model.ChannelInfoResDTO;
import com.identity.auth.service.enums.ResCodeEnum;
import com.identity.auth.service.manager.BusinessOrderManager;
import com.identity.auth.service.model.req.IdentityAuthReqDTO;
import com.identity.auth.service.model.res.IdentityAuthResDTO;
import com.identity.auth.service.util.SerialNoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 身份认证渠道处理
 * Created by lijing on 2017/11/9 0009.
 */
@Slf4j
@Component
public class IdentityProcess {
    /** 身份认证渠道 */
    @Autowired
    private IdentityChannelFacade channelFacade;

    @Autowired
    private BusinessOrderManager businessOrderManager;

    public IdentityAuthResDTO channelAuth(String tradeNo,IdentityAuthReqDTO reqDTO, ChannelInfoResDTO channelInfoResDTO){
        IdentityReqDTO channelReq = buildReq(reqDTO,channelInfoResDTO.getChannelId());
        TBusinessOrderInfo orderInfo = businessOrderManager.createBusinessOrder(tradeNo,channelInfoResDTO.getChannelId());
        IdentityAuthResult<IdentityResDTO> result = channelFacade.identityAuth(channelReq);
        if(result.isSuccess()){
            //todo 成功订单更新
//            orderInfo.setFeeFlag();
            return buildRes(result.getData());
        }
        //todo 失败订单更新
        return null;
//        businessOrderManager.updateBusinessOrder(tradeNo,channelInfoResDTO.getChannelId());
    }

    /**
     * 构建渠道请求
     * @param authReqDTO 请求信息
     * @param channelId 渠道id
     * @return 渠道请求
     */
    private IdentityReqDTO buildReq(IdentityAuthReqDTO authReqDTO,String channelId){
        IdentityReqDTO reqDTO = new IdentityReqDTO();
        reqDTO.setIdCard(authReqDTO.getIdCard());
        reqDTO.setIdName(authReqDTO.getIdName());
        reqDTO.setChannelId(channelId);
        reqDTO.setChannelReqNo(SerialNoUtil.getSerialNo());
        return reqDTO;
    }

    /**
     * 封装响应
     * @param res 渠道响应
     * @return 封装后的响应
     */
    private IdentityAuthResDTO buildRes(IdentityResDTO res){
        IdentityAuthResDTO resDTO = new IdentityAuthResDTO();
        resDTO.setIdCard(res.getIdCard());
        resDTO.setIdName(res.getIdName());
        ResCodeEnum resCodeEnum = buildResEnum(res.getResCode());
        resDTO.setResCode(resCodeEnum.getCode());
        resDTO.setResMsg(resCodeEnum.getDesc());
        return resDTO;
    }

    /**
     * 错误码枚举转换
     * @param resCode 渠道响应code
     * @return 转换后的枚举code
     */
    private ResCodeEnum buildResEnum(String resCode){
        for (ResCodeEnum resCodeEnum:ResCodeEnum.values()){
            if(resCodeEnum.getCode().equals(resCode)){
                return resCodeEnum;
            }
        }
        return ResCodeEnum.AUTH_ERROR;
    }
}
