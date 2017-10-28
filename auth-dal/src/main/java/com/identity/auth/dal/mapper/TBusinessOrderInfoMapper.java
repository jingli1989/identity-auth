package com.identity.auth.dal.mapper;

import com.identity.auth.dal.model.TBusinessOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * t_business_order_info(渠道请求订单) 数据库操作
 * @author lijing
 * date:2017/10/26 09:17
 */
public interface TBusinessOrderInfoMapper {

    /** 
     * 添加对象所有字段
     * @param record 插入字段对象(必须含ID）
     */
    int insert(TBusinessOrderInfo record);

    /**
     * 根据ID查询
     * @param id 主键ID
     */
    TBusinessOrderInfo selectByPrimaryKey(Integer id);

    /**
     * 根据tradeNo查询
     * @param tradeNo 内部流水号
     * @return 查询结果
     */
    List<TBusinessOrderInfo> selectByTradeNo(@Param(value = "tradeNo")String tradeNo);

    /**
     * 根据businessNo查询
     * @param businessNo 业务流水号
     * @return 查询结果
     */
    TBusinessOrderInfo selectByBusinessNo(@Param(value = "businessNo")String businessNo);

    /**
     * 根据id更新业务流水信息
     * @param record 业务流水信息(含ID)
     * @return 更新记录数
     */
    int updateByPrimaryKeySelective(TBusinessOrderInfo record);

}