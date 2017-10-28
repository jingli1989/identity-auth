package com.identity.auth.member.manager;

import com.identity.auth.common.annotation.SysLogId;
import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.dal.mapper.TChannelInfoMapper;
import com.identity.auth.dal.model.TChannelInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 渠道管理
 * Created by lijing on 2017/10/26 0026.
 */
@Slf4j
@Component
public class ChannelManager {
    /** 渠道信息数据库操作 */
    @Autowired
    private TChannelInfoMapper channelInfoMapper;

    /**
     * 根据渠道ID查询渠道信息
     * @param channelId 渠道ID
     * @return 渠道信息
     */
    @SysLogId("数据库-查询渠道信息")
    public TChannelInfo selectByChannelId(String channelId){
        return channelInfoMapper.selectByPrimaryKey(channelId);
    }

    @SysLogId("数据库-查询渠道信息")
    public boolean addChannel(TChannelInfo channelInfo){
        TChannelInfo sel = channelInfoMapper.selectByPrimaryKey(channelInfo.getChannelId());
        if(sel!=null){
            log.error("新增渠道信息:{},渠道ID已存在,新增失败",channelInfo);
            throw new BusinessException(ErrorCodeEnum.DB_DATA_IS_EXITS.getCode(),ErrorCodeEnum.DB_DATA_IS_EXITS.getMsg());
        }
        int i = channelInfoMapper.insert(channelInfo);
        if(i==1){
            return true;
        }
        throw new BusinessException(ErrorCodeEnum.DB_DATA_IS_EXITS.getCode(),ErrorCodeEnum.DB_DATA_IS_EXITS.getMsg());
    }

    /**
     * 根据渠道ID更新渠道信息
     * @param channelInfo 渠道信息
     * @return 更新结果 true 更新成功
     */
    @SysLogId("数据库-更新渠道信息")
    public boolean updateChannel(TChannelInfo channelInfo){
        TChannelInfo sel = channelInfoMapper.selectByPrimaryKey(channelInfo.getChannelId());
        if(sel!=null&&sel.getChannelId().equals(channelInfo.getChannelId())){
            log.error("更新渠道信息:{},渠道ID已存在,更新失败",channelInfo);
            throw new BusinessException(ErrorCodeEnum.DB_DATA_IS_EXITS.getCode(),ErrorCodeEnum.DB_DATA_IS_EXITS.getMsg());
        }
        int i = channelInfoMapper.insert(channelInfo);
        if(i==1){
            return true;
        }
        throw new BusinessException(ErrorCodeEnum.DB_DATA_IS_EXITS.getCode(),ErrorCodeEnum.DB_DATA_IS_EXITS.getMsg());
    }




}
