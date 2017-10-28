package com.identity.auth.dal.mapper;

import com.identity.auth.dal.model.TMemberProductInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * t_member_product_info(商户开通产品信息) 数据库操作
 * @author lijing
 * date:2017/10/26 09:17
 */
public interface TMemberProductInfoMapper {
    /** 
     * 根据ID删除
     * @param id 主键ID
     */
    int deleteByPrimaryKey(Integer id);

    /** 
     * 添加对象所有字段
     * @param record 插入字段对象(必须含ID）
     */
    int insert(TMemberProductInfo record);

    /**
     * 根据id查询商户开通产品信息
     * @param id id
     * @return 商户开通产品信息
     */
    TMemberProductInfo selectById(Integer id);

    /**
     * 根据商户编号及状态查询商户开通产品(不关联校验商户及产品状态)
     * @param memberId 商户编号
     * @param openStatus 开通状态
     * @return 商户开通产品信息
     */
    List<TMemberProductInfo> selectByMemberId(@Param(value = "memberId")String memberId,@Param(value = "openStatus")String openStatus);

    /** 
     * 根据ID修改对应字段
     * @param record 修改字段对象(必须含ID）
     */
    int updateByPrimaryKeySelective(TMemberProductInfo record);

    /**
     * 根据唯一索引查询商户产品信息
     * @param memberId 商户id
     * @param productId 产品id
     * @param openStatus 开通状态
     * @return 商户开通产品信息
     */
    TMemberProductInfo selectMemberProductById(@Param(value = "memberId")String memberId,@Param(value = "productId")String productId,@Param(value = "openStatus")String openStatus);

    /**
     * 根据商户编号及状态查询商户开通产品(关联校验商户及产品状态)
     * @param memberId 商户编号
     * @param openStatus 开通状态
     * @param productId 功能ID(可空)
     * @return 商户开通产品信息
     */
    List<TMemberProductInfo> selectMemberOpenProduct(@Param(value = "memberId")String memberId,@Param(value = "openStatus")String openStatus,@Param(value = "productId")String productId);
}