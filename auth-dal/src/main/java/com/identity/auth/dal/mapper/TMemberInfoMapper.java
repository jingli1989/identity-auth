package com.identity.auth.dal.mapper;

import com.identity.auth.dal.model.TMemberInfo;

/**
 * t_member_info(商户信息表) 数据库操作
 * @author lijing
 * date:2017/10/26 09:17
 */
public interface TMemberInfoMapper {


    /** 
     * 添加对象对应字段
     * @param record 插入字段对象(必须含ID）
     */
    int insertSelective(TMemberInfo record);

    /** 
     * 根据ID查询
     * @param id 主键ID
     */
    TMemberInfo selectByPrimaryKey(Integer id);

    /** 
     * 根据ID修改对应字段
     * @param record 修改字段对象(必须含ID）
     */
    int updateByPrimaryKeySelective(TMemberInfo record);

    /**
     * 根据商户号查询商户信息
     * @param memberId 商户号
     * @return 商户信息
     */
    TMemberInfo selectByMemberId(String memberId);
}