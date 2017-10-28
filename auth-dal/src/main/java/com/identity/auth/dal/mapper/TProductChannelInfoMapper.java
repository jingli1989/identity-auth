package com.identity.auth.dal.mapper;

import com.identity.auth.dal.model.TProductChannelInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * t_product_channel_info(产品渠道开通信息) 数据库操作
 * @author lijing
 * date:2017/10/26 09:17
 */
public interface TProductChannelInfoMapper {
    /** 
     * 根据ID删除
     * @param id 主键ID
     */
    int deleteByPrimaryKey(Integer id);

    /** 
     * 添加对象所有字段
     * @param record 插入字段对象(必须含ID）
     */
    int insert(TProductChannelInfo record);

    /** 
     * 根据ID查询
     * @param id 主键ID
     */
    TProductChannelInfo selectByPrimaryKey(Integer id);

    /**
     * 根据唯一索引查询
     * @param productId 产品id
     * @param channelId 渠道id
     * @param openStatus 状态
     * @return 产品渠道信息
     */
    TProductChannelInfo selectByUnKey(@Param(value = "productId")String productId,@Param(value = "channelId")String channelId,@Param(value = "openStatus")String openStatus);

    /**
     * 根据产品id查询
     * @param productId 产品id
     * @param channelId 渠道id
     * @param openStatus 状态
     * @return 产品渠道信息
     */
    List<TProductChannelInfo> selectByAll(@Param(value = "productId")String productId,@Param(value = "channelId")String channelId,@Param(value = "openStatus")String openStatus);

    /** 
     * 根据ID修改对应字段
     * @param record 修改字段对象(必须含ID）
     */
    int updateByPrimaryKeySelective(TProductChannelInfo record);

    /**
     * 根据产品id和状态查询产品渠道信息
     * @param productId 产品id
     * @param openStatus 状态
     * @return 渠道信息
     */
    List<TProductChannelInfo> selectProductChannel(@Param(value = "productId")String productId, @Param(value = "openStatus")String openStatus);
}