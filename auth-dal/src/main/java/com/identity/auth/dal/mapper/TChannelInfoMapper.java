package com.identity.auth.dal.mapper;

import com.identity.auth.dal.model.TChannelInfo;

/**
 * t_channel_info(渠道信息表) 数据库操作
 * @author lijing
 * date:2017/10/26 09:17
 */
public interface TChannelInfoMapper {

    /** 
     * 添加对象所有字段
     * @param record 插入字段对象(必须含ID）
     */
    int insert(TChannelInfo record);

    /** 
     * 根据渠道id查询
     * @param channelId 渠道id
     */
    TChannelInfo selectByPrimaryKey(String channelId);

    /** 
     * 根据ID修改对应字段
     * @param record 修改字段对象(必须含ID）
     */
    int updateByPrimaryKeySelective(TChannelInfo record);

}