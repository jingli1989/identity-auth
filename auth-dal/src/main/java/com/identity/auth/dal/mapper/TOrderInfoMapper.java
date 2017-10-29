package com.identity.auth.dal.mapper;

import com.identity.auth.dal.model.TOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据条件查询订单
     * @param record 查询条件
     * @return 查询结果
     */
    List<TOrderInfo> selectBySelective(TOrderInfo record);

    /**
     * 根据商户号和商户订单号查询订单信息
     * @param memberId 商户号
     * @param memberTransId 商户订单号
     * @return 订单信息
     */
    TOrderInfo selectByMemberTrans(@Param(value = "memberId")String memberId, @Param(value = "memberTransId")String memberTransId);

}