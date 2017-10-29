package com.identity.auth.manager;

import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.common.util.SystemConstant;
import com.identity.auth.dal.mapper.TOrderInfoMapper;
import com.identity.auth.dal.model.TOrderInfo;
import com.identity.auth.enums.FeeFlagEnum;
import com.identity.auth.enums.OrderStatusEnum;
import com.identity.auth.member.model.MemberProductCheckResDTO;
import com.identity.auth.model.req.BaseReqDTO;
import com.identity.auth.model.res.BaseResDTO;
import com.identity.auth.util.DateUtil;
import com.identity.auth.util.SerialNoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单信息管理
 * Created by lijing on 2017/10/26 0026.
 */
@Slf4j
@Component
public class OrderManager {

    /** 订单数据库操作 */
    @Autowired(required = false)
    private TOrderInfoMapper orderInfoMapper;

    /**
     * 创建主订单
     * @param baseReqDTO 基本请求信息
     * @param checkResDTO 商户产品校验结果
     * @return 订单信息
     */
    public TOrderInfo createOrder(BaseReqDTO baseReqDTO, MemberProductCheckResDTO checkResDTO){
       TOrderInfo orderInfo = orderInfoMapper.selectByMemberTrans(baseReqDTO.getMemberId(),baseReqDTO.getMemberTransId());
       if(orderInfo != null){
           log.error("商户请求:{},订单号已存在",baseReqDTO);
           throw new BusinessException(ErrorCodeEnum.TRANS_ID_IS_EXITS.getCode(),ErrorCodeEnum.TRANS_ID_IS_EXITS.getMsg());
       }
       orderInfo = initOrder(baseReqDTO, checkResDTO);
       int i = orderInfoMapper.insert(orderInfo);
       if(i==1){
           return orderInfo;
       }
       throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }

    /**
     * 更新订单结果
     * @param baseResDTO 响应结果
     * @param businessNo 渠道处理流水
     * @param orderInfo 原订单信息
     */
    public void updateSuccessOrder(BaseResDTO baseResDTO, String businessNo,TOrderInfo orderInfo){
        TOrderInfo update = new TOrderInfo();
        update.setId(orderInfo.getId());
        update.setBusinessNo(businessNo);
        update.setResCode(baseResDTO.getResCode());
        update.setResMsg(baseResDTO.getResMsg());
        update.setFeeFlag(baseResDTO.getFeeFlag());
        update.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
        update.setUpdatedBy(SystemConstant.SYSTEM_USER);
        int i = orderInfoMapper.updateByPrimaryKeySelective(update);
        if(i!=1){
            throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    /**
     * 更新处理失败订单
     * @param orderInfo 原订单信息
     * @param errorCode 错误代码
     * @param errorMsg 错误描述
     */
    public void updateFailOrder(TOrderInfo orderInfo,String errorCode,String errorMsg){
        TOrderInfo update = new TOrderInfo();
        update.setId(orderInfo.getId());
        update.setResCode(errorCode);
        update.setResMsg(errorMsg);
        update.setFeeFlag(FeeFlagEnum.N.getCode());
        update.setOrderStatus(OrderStatusEnum.FAIL.getCode());
        update.setUpdatedBy(SystemConstant.SYSTEM_USER);
        int i = orderInfoMapper.updateByPrimaryKeySelective(update);
        if(i!=1){
            throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    /**
     * 初始化主订单信息
     * @param baseReqDTO 基本请求信息
     * @param checkResDTO 商户产品校验结果
     * @return 订单信息
     */
    private TOrderInfo initOrder(BaseReqDTO baseReqDTO, MemberProductCheckResDTO checkResDTO){
        TOrderInfo orderInfo = new TOrderInfo();
        orderInfo.setMemberId(checkResDTO.getMemberId());
        orderInfo.setMemberTransDate(DateUtil.parse(baseReqDTO.getMemberTransDate(),DateUtil.YYYYMMDDHHmmSS));
        orderInfo.setMemberTransId(baseReqDTO.getMemberTransId());
        orderInfo.setProductId(checkResDTO.getProductId());
        orderInfo.setOrderMoney(checkResDTO.getUnitPrice());
        orderInfo.setOrderStatus(OrderStatusEnum.INIT.getCode());
        orderInfo.setFeeFlag(FeeFlagEnum.N.getCode());
        orderInfo.setTradeNo(SerialNoUtil.getSerialNo());
        orderInfo.setCreatedBy(SystemConstant.SYSTEM_USER);
        return orderInfo;
    }
}
