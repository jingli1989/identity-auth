package com.identity.auth.dal.mapper;

import com.identity.auth.dal.model.TOrderInfo;

/**
 * t_order_info(订单表) 数据库操作
 * @author lijing
 * date:2017/10/26 09:17
 */
public interface TOrderInfoMapper {

    /** 
     * 添加对象所有字段
     * @param record 插入字段对象(必须含ID）
     */
    int insert(TOrderInfo record);


    /** 
     * 根据ID查询
     * @param id 主键ID
     */
    TOrderInfo selectByPrimaryKey(Integer id);

    /** 
     * 根据ID修改对应字段
     * @param record 修改字段对象(必须含ID）
     */
    int updateByPrimaryKeySelective(TOrderInfo record);

}