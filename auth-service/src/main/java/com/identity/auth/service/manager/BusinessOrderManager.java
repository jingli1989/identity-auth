package com.identity.auth.service.manager;

import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.common.util.SystemConstant;
import com.identity.auth.dal.mapper.TBusinessOrderInfoMapper;
import com.identity.auth.dal.model.TBusinessOrderInfo;
import com.identity.auth.service.enums.FeeFlagEnum;
import com.identity.auth.service.util.SerialNoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 业务流水单管理
 * Created by lijing on 2017/10/26 0026.
 */
@Slf4j
@Component
public class BusinessOrderManager {

    /** 业务流水数据库操作 */
    @Autowired(required = false)
    private TBusinessOrderInfoMapper businessOrderInfoMapper;


    /**
     * 创建业务流水号
     * @param tradeNo 订单号
     * @param channelId 渠道id
     * @return 业务流水记录
     */
    public TBusinessOrderInfo createBusinessOrder(String tradeNo,String channelId){
        TBusinessOrderInfo businessOrderInfo = build(tradeNo, channelId);
        int i = businessOrderInfoMapper.insert(businessOrderInfo);
        if(i==1) {
            return businessOrderInfo;
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }

    /**
     * 更新业务流水
     * @param orderInfo 业务流水订单(含id)
     */
    public void updateBusinessOrder(TBusinessOrderInfo orderInfo){
        int i = businessOrderInfoMapper.updateByPrimaryKeySelective(orderInfo);
        if(i==1) {
            return;
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }



    /**
     * 构建业务流水订单
     * @param tradeNo 系统订单
     * @param channelId 渠道id
     * @return 业务流水订单
     */
    private TBusinessOrderInfo build(String tradeNo,String channelId){
        TBusinessOrderInfo orderInfo = new TBusinessOrderInfo();
        orderInfo.setBusinessNo(SerialNoUtil.getSerialNo());
        orderInfo.setChannelId(channelId);
        orderInfo.setTradeNo(tradeNo);
        //todo 零时设置 可能不同渠道需要不同请求流水号格式
        orderInfo.setReqNo(SerialNoUtil.getSerialNo());
        orderInfo.setFeeFlag(FeeFlagEnum.N.getCode());
        orderInfo.setCreatedBy(SystemConstant.SYSTEM_USER);
        return orderInfo;
    }

}
