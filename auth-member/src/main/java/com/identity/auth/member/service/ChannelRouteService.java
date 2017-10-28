package com.identity.auth.member.service;

import com.identity.auth.common.annotation.SysLogId;
import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.common.util.BeanMapperUtil;
import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.common.util.LogUtil;
import com.identity.auth.dal.model.TProductChannelInfo;
import com.identity.auth.member.api.ChannelFacade;
import com.identity.auth.member.manager.ProductChannelManager;
import com.identity.auth.member.model.ChannelInfoResDTO;
import com.identity.auth.member.util.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 渠道路由服务
 * Created by lijing on 2017/10/26 0026.
 */
@Slf4j
@Service
public class ChannelRouteService implements ChannelFacade{

    /** 产品渠道管理 */
    private final ProductChannelManager productChannelManager;

    @Autowired
    public ChannelRouteService(ProductChannelManager productChannelManager) {
        this.productChannelManager = productChannelManager;
    }

    /**
     * 获取产品渠道信息
     *
     * @param productId 产品id
     * @param logId 日志id
     * @return 渠道信息
     */
    @SysLogId("获取产品渠道")
    @Override
    public IdentityAuthResult<List<ChannelInfoResDTO>> channelRoute(String productId,String logId) {
        try {
            LogUtil.updateLogId(logId);
            log.info("产品:{},获取产品渠道",productId);
            List<ChannelInfoResDTO> resDTOList = CacheUtil.getProductChannel(productId);
            if(resDTOList!=null){
                return new IdentityAuthResult<>(resDTOList);
            }
            List<TProductChannelInfo> productChannelInfoList = productChannelManager.selectAndCheckProductChannel(productId);
            if(productChannelInfoList==null||productChannelInfoList.isEmpty()){
                return new IdentityAuthResult<>(ErrorCodeEnum.PRODUCT_CHANNEL_NOT_EXITS.getCode(),ErrorCodeEnum.PRODUCT_CHANNEL_NOT_EXITS.getMsg());
            }
            resDTOList = BeanMapperUtil.mapList(productChannelInfoList,ChannelInfoResDTO.class);
            CacheUtil.cacheProductChannel(productId,resDTOList);
            return new IdentityAuthResult<>(resDTOList);
        }catch (BusinessException be){
            return new IdentityAuthResult<>(be.getCode(),be.getResMessage());
        }catch (Exception e){
            log.error("商户产品校验失败，原因:",e);
            return new IdentityAuthResult<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }

    }
}
