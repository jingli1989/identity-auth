package com.identity.auth.service;

import com.identity.auth.common.annotation.SysLogId;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.common.util.BeanMapperUtil;
import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.common.util.LogUtil;
import com.identity.auth.dal.model.TOrderInfo;
import com.identity.auth.member.api.ChannelFacade;
import com.identity.auth.member.api.MemberAuthFacade;
import com.identity.auth.member.model.ChannelInfoResDTO;
import com.identity.auth.member.model.MemberInfoResDTO;
import com.identity.auth.member.model.ProductInfoResDTO;
import com.identity.auth.service.manager.OrderManager;
import com.identity.auth.service.model.ChannelProcessDTO;
import com.identity.auth.service.model.req.IdentityAuthReqDTO;
import com.identity.auth.service.model.res.IdentityAuthResDTO;
import com.identity.auth.service.process.IdentityProcess;
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
    /** 渠道认证处理 */
    @Autowired
    private IdentityProcess identityProcess;

    /**
     * 身份认证
     * @param reqDTO 认证请求
     * @return 认证结果
     */
    @SysLogId("服务层-身份认证")
    public IdentityAuthResDTO authReq(IdentityAuthReqDTO reqDTO){

        IdentityAuthResult<ProductInfoResDTO> productResult = memberAuthFacade.productCheck(reqDTO.getMemberId(),"ID_CARD_AUTH",LogUtil.getLogId());
        if(!productResult.isSuccess()){
            throw new BusinessException(productResult.getErrorCode(),productResult.getErrorMsg());
        }
        ProductInfoResDTO product = productResult.getData();
        TOrderInfo orderInfo = orderManager.createOrder(reqDTO,product.getProductId(),product.getUnitPrice());
        IdentityAuthResult<List<ChannelInfoResDTO>> channelResult = channelFacade.channelRoute(product.getProductId(),LogUtil.getLogId());
        if(!channelResult.isSuccess()){
            orderManager.updateFailOrder(orderInfo,channelResult.getErrorCode(),channelResult.getErrorMsg());
            throw new BusinessException(channelResult.getErrorCode(),channelResult.getErrorMsg());
        }
        ChannelInfoResDTO channelInfoResDTO = channelResult.getData().get(0);

        ChannelProcessDTO processDTO = identityProcess.channelAuth(orderInfo.getTradeNo(),reqDTO,channelInfoResDTO);
        IdentityAuthResDTO authResDTO = buildResDTO(processDTO,reqDTO,orderInfo);
        orderManager.updateSuccessOrder(authResDTO,processDTO.getBusinessNo(),orderInfo);
        return authResDTO;
    }

    /**
     * 获取商户密码
     * @param memberId 商户号
     * @return 商户密码
     */
    @SysLogId("服务层-校验商户并获取商户密码")
    public String getMemberSecurityKey(String memberId){
        IdentityAuthResult<MemberInfoResDTO> result = memberAuthFacade.memberCheck(memberId, LogUtil.getLogId());
        if(!result.isSuccess()){
            throw new BusinessException(result.getErrorCode(),result.getErrorMsg());
        }
        MemberInfoResDTO resDTO = result.getData();
        return resDTO.getSecurityKey();
    }

    /**
     * 构建认证响应
     * @param processDTO 渠道处理结果
     * @param reqDTO 请求信息
     * @param orderInfo 订单信息
     * @return 认证响应
     */
    private IdentityAuthResDTO buildResDTO(ChannelProcessDTO processDTO,IdentityAuthReqDTO reqDTO,TOrderInfo orderInfo){
        IdentityAuthResDTO resDTO = BeanMapperUtil.objConvert(reqDTO,IdentityAuthResDTO.class);
        resDTO.setFeeFlag(processDTO.getFeeFlag());
        resDTO.setResCode(processDTO.getResCode());
        resDTO.setResMsg(processDTO.getResMsg());
        resDTO.setOrderMoney(orderInfo.getOrderMoney()+"");
        resDTO.setTradeNo(orderInfo.getTradeNo());
        return resDTO;
    }

}
