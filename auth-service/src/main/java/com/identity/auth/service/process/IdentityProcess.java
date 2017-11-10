package com.identity.auth.service.process;

import com.identity.auth.channel.api.IdentityChannelFacade;
import com.identity.auth.channel.model.IdentityReqDTO;
import com.identity.auth.channel.model.IdentityResDTO;
import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.dal.model.TBusinessOrderInfo;
import com.identity.auth.member.model.ChannelInfoResDTO;
import com.identity.auth.service.enums.FeeFlagEnum;
import com.identity.auth.service.enums.ResCodeEnum;
import com.identity.auth.service.manager.BusinessOrderManager;
import com.identity.auth.service.model.ChannelProcessDTO;
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

    /**
     * 渠道认证
     * @param tradeNo 订单号
     * @param reqDTO 认证请求参数
     * @param channelInfoResDTO 渠道信息
     * @return 渠道处理结果
     */
    public ChannelProcessDTO channelAuth(String tradeNo,IdentityAuthReqDTO reqDTO, ChannelInfoResDTO channelInfoResDTO){
        IdentityReqDTO channelReq = buildReq(reqDTO,channelInfoResDTO.getChannelId());
        TBusinessOrderInfo orderInfo = businessOrderManager.createBusinessOrder(tradeNo,channelInfoResDTO.getChannelId());
        IdentityAuthResult<IdentityResDTO> result = channelFacade.identityAuth(channelReq);
        if(result.isSuccess()){
            IdentityResDTO resDTO = result.getData();
            orderInfo.setFeeFlag(resDTO.getChannelFee()?FeeFlagEnum.Y.getCode():FeeFlagEnum.N.getCode());
            orderInfo.setReqNo(resDTO.getChannelReqNo());
            orderInfo.setResNo(resDTO.getChannelResNo());
            orderInfo.setResCode(resDTO.getResCode());
            orderInfo.setResMsg(resDTO.getResDesc());
            orderInfo.setChannelTime(resDTO.getChannelTime());
            businessOrderManager.updateBusinessOrder(orderInfo);
            return buildProcessDTO(resDTO,orderInfo);
        }
        orderInfo.setFeeFlag(FeeFlagEnum.N.getCode());
        orderInfo.setResCode(result.getErrorCode());
        orderInfo.setResMsg(result.getErrorMsg());
        return buildProcessDTO(null,orderInfo);
    }

    /**
     * 构建渠道处理响应
     * @param resDTO 渠道响应
     * @param orderInfo 业务流水订单
     * @return 渠道处理响应
     */
    private ChannelProcessDTO buildProcessDTO(IdentityResDTO resDTO,TBusinessOrderInfo orderInfo){
        ChannelProcessDTO processDTO = new ChannelProcessDTO();
        processDTO.setBusinessNo(orderInfo.getBusinessNo());
        processDTO.setResCode(ResCodeEnum.AUTH_ERROR.getCode());
        processDTO.setResMsg(ResCodeEnum.AUTH_ERROR.getDesc());
        processDTO.setFeeFlag(FeeFlagEnum.N.getCode());
        processDTO.setTradeNo(orderInfo.getTradeNo());
        if(resDTO!=null){
            ResCodeEnum resCodeEnum = buildResEnum(resDTO.getResCode());
            String feeFlag = ResCodeEnum.AUTH_ERROR.getCode().equals(resCodeEnum.getCode())?FeeFlagEnum.N.getCode():FeeFlagEnum.Y.getCode();
            processDTO.setFeeFlag(feeFlag);
            processDTO.setResCode(resCodeEnum.getCode());
            processDTO.setResMsg(resCodeEnum.getDesc());
        }
        return processDTO;
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
