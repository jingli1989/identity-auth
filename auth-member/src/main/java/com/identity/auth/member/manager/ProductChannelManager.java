package com.identity.auth.member.manager;

import com.identity.auth.common.annotation.SysLogId;
import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.dal.mapper.*;
import com.identity.auth.dal.model.*;
import com.identity.auth.member.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 产品渠道管理
 * Created by lijing on 2017/10/26 0026.
 */
@Slf4j
@Component
public class ProductChannelManager {

    /** 渠道信息数据库操作 */
    @Autowired
    private TChannelInfoMapper channelInfoMapper;

    /** 产品信息数据库操作 */
    @Autowired
    private TProductInfoMapper productInfoMapper;

    /** 产品渠道关系维护数据库操作 */
    @Autowired
    private TProductChannelInfoMapper productChannelInfoMapper;


    /**
     * 产品渠道开通
     * @param productChannelInfo 产品渠道信息
     * @return 产品渠道开通结果 true 开通成功
     */
    @SysLogId("数据库-产品渠道开通")
    public boolean openMemberProduct(TProductChannelInfo productChannelInfo){
        TChannelInfo channelInfo = channelInfoMapper.selectByPrimaryKey(productChannelInfo.getChannelId());
        if(channelInfo==null|| !StatusEnum.NORMAL.getCode().equals(channelInfo.getChannelStatus())){
            log.info("产品渠道开通信息:{},渠道不存在，开通失败",productChannelInfo);
            throw new BusinessException(ErrorCodeEnum.CHANNEL_NOT_EXITS.getCode(),ErrorCodeEnum.CHANNEL_NOT_EXITS.getMsg());
        }
        TProductInfo productInfo = productInfoMapper.selectByProductId(productChannelInfo.getProductId());
        if(productInfo==null||!StatusEnum.NORMAL.getCode().equals(productInfo.getProductStatus())){
            log.info("产品渠道开通信息:{},产品不存在，开通失败",productChannelInfo);
            throw new BusinessException(ErrorCodeEnum.PRODUCT_NOT_EXITS.getCode(),ErrorCodeEnum.PRODUCT_NOT_EXITS.getMsg());
        }
        TProductChannelInfo sel = productChannelInfoMapper.selectByUnKey(productChannelInfo.getProductId(),productChannelInfo.getChannelId(),null);
        if(sel != null){
            log.info("产品渠道开通信息:{},产品渠道已存在，开通失败",productChannelInfo);
            throw new BusinessException(ErrorCodeEnum.PRODUCT_CHANNEL_IS_EXITS.getCode(),ErrorCodeEnum.PRODUCT_CHANNEL_IS_EXITS.getMsg());
        }
        int i = productChannelInfoMapper.insert(productChannelInfo);
        if(i==1){
            return true;
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }

    /**
     * 关闭产品渠道
     * @param id 产品渠道关闭id
     * @return 产品渠道关闭结果 true 关闭成功
     */
    @SysLogId("数据库-产品渠道关闭")
    public boolean closeMemberProduct(Integer id){
        TProductChannelInfo sel = productChannelInfoMapper.selectByPrimaryKey(id);
        if(sel==null||!StatusEnum.NORMAL.getCode().equals(sel.getOpenStatus())){
            log.info("产品渠道关闭信息:{},产品渠道未开通，关闭失败",id);
            throw new BusinessException(ErrorCodeEnum.PRODUCT_CHANNEL_NOT_EXITS.getCode(),ErrorCodeEnum.PRODUCT_CHANNEL_NOT_EXITS.getMsg());
        }
        TProductChannelInfo update = new TProductChannelInfo();
        update.setId(sel.getId());
        update.setOpenStatus(StatusEnum.DISABLE.getCode());
        int i = productChannelInfoMapper.updateByPrimaryKeySelective(update);
        if(i==1){
            return true;
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }

    /**
     * 更新产品渠道信息
     * @param productChannelInfo 需要更新的产品渠道信息(含id)
     * @return 产品渠道更新结果 true 更新成功
     */
    @SysLogId("数据库-产品渠道更新")
    public boolean updateMemberProduct(TProductChannelInfo productChannelInfo){
        TProductChannelInfo sel = productChannelInfoMapper.selectByUnKey(productChannelInfo.getProductId(),productChannelInfo.getChannelId(),null);
        if(sel != null&&sel.getId().intValue()!=productChannelInfo.getId().intValue()){
            log.info("产品渠道更新信息:{},产品渠道已存在，更新失败",productChannelInfo);
            throw new BusinessException(ErrorCodeEnum.PRODUCT_CHANNEL_IS_EXITS.getCode(),ErrorCodeEnum.PRODUCT_CHANNEL_IS_EXITS.getMsg());
        }
        int i = productChannelInfoMapper.updateByPrimaryKeySelective(productChannelInfo);
        if(i==1){
            return true;
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }

    /**
     * 查询产品所有渠道信息
     * @param productId 产品id
     * @param channelId 渠道id(可空)
     * @param openStatus 状态(可空)
     * @return 产品渠道信息
     */
    @SysLogId("数据库-查询产品所有渠道")
    public List<TProductChannelInfo> selectProductChannel(String productId,String channelId,String openStatus){
        return productChannelInfoMapper.selectByAll(productId, channelId, openStatus);
    }

    /**
     * 检查并查询产品渠道信息
     * @param productId 产品id
     * @return 产品渠道信息
     */
    @SysLogId("数据库-检查并查询产品渠道")
    public List<TProductChannelInfo> selectAndCheckProductChannel(String productId){
        return productChannelInfoMapper.selectProductChannel(productId,StatusEnum.NORMAL.getCode());
    }



}
