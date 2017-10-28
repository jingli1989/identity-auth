package com.identity.auth.dal.mapper;

import com.identity.auth.dal.model.TProductInfo;

/**
 * t_product_info(产品信息表) 数据库操作
 * @author lijing
 * date:2017/10/26 09:17
 */
public interface TProductInfoMapper {

    /** 
     * 添加对象所有字段
     * @param record 插入字段对象(必须含ID）
     */
    int insert(TProductInfo record);

    /** 
     * 根据productId查询
     * @param productId 产品id
     */
    TProductInfo selectByProductId(String productId);


    /** 
     * 根据ID修改对应字段
     * @param record 修改字段对象(必须含ID）
     */
    int updateByPrimaryKeySelective(TProductInfo record);

}