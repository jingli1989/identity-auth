package com.identity.auth.service;

import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.common.util.BeanMapperUtil;
import com.identity.auth.common.util.HMacSHAUtil;
import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.common.util.LogUtil;
import com.identity.auth.dal.model.TOrderInfo;
import com.identity.auth.member.api.ChannelFacade;
import com.identity.auth.member.api.MemberAuthFacade;
import com.identity.auth.member.model.ChannelInfoResDTO;
import com.identity.auth.member.model.MemberInfoResDTO;
import com.identity.auth.member.model.ProductInfoResDTO;
import com.identity.auth.service.enums.FeeFlagEnum;
import com.identity.auth.service.enums.ResCodeEnum;
import com.identity.auth.service.manager.OrderManager;
import com.identity.auth.service.model.HeaderAuthDTO;
import com.identity.auth.service.model.req.IdentityAuthReqDTO;
import com.identity.auth.service.model.res.IdentityAuthResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public IdentityAuthResDTO authReq(IdentityAuthReqDTO reqDTO,String reqBody, HeaderAuthDTO authDTO){
        IdentityAuthResult<MemberInfoResDTO> result = memberAuthFacade.memberCheck(reqDTO.getMemberId(), LogUtil.getLogId());
        if(!result.isSuccess()){
            throw new BusinessException(result.getErrorCode(),result.getErrorMsg());
        }
        MemberInfoResDTO resDTO = result.getData();
        boolean flag = HMacSHAUtil.HMacSHA256Check(reqBody,authDTO.getAuthCode(),resDTO.getSecurityKey());
        if(!flag){
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR.getCode(),"请求信息校验失败");
        }
        IdentityAuthResult<ProductInfoResDTO> productResult = memberAuthFacade.productCheck(resDTO.getMemberId(),"ID_CARD_AUTH",LogUtil.getLogId());
        if(!productResult.isSuccess()){
            throw new BusinessException(result.getErrorCode(),result.getErrorMsg());
        }
        ProductInfoResDTO product = productResult.getData();
        TOrderInfo orderInfo = orderManager.createOrder(reqDTO,product.getProductId(),product.getUnitPrice());
        IdentityAuthResult<List<ChannelInfoResDTO>> channelResult = channelFacade.channelRoute(product.getProductId(),LogUtil.getLogId());
        if(!channelResult.isSuccess()){
            orderManager.updateFailOrder(orderInfo,channelResult.getErrorCode(),channelResult.getErrorMsg());
            throw new BusinessException(result.getErrorCode(),result.getErrorMsg());
        }
        ChannelInfoResDTO channelInfoResDTO = channelResult.getData().get(0);

        IdentityAuthResDTO authResDTO = channelProcess(reqDTO,channelInfoResDTO,orderInfo);

        orderManager.updateSuccessOrder(authResDTO,"",orderInfo);

        return authResDTO;

    }


    private IdentityAuthResDTO channelProcess(IdentityAuthReqDTO reqDTO,ChannelInfoResDTO channelInfoResDTO,TOrderInfo orderInfo){
        //todo 渠道请求
        IdentityAuthResDTO resDTO = BeanMapperUtil.objConvert(reqDTO,IdentityAuthResDTO.class);



        resDTO.setFeeFlag(FeeFlagEnum.Y.getCode());
        resDTO.setOrderMoney(orderInfo.getOrderMoney()+"");
        resDTO.setTradeNo(orderInfo.getTradeNo());
        resDTO.setResCode(ResCodeEnum.MATE.getCode());
        resDTO.setResMsg(ResCodeEnum.MATE.getDesc());
        return resDTO;
    }


}
