package com.identity.auth.member.manager;

import com.identity.auth.common.annotation.SysLogId;
import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.dal.mapper.TMemberInfoMapper;
import com.identity.auth.dal.model.TMemberInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商户管理
 * Created by lijing on 2017/10/26 0026.
 */
@Slf4j
@Component
public class MemberManager {

    /** 商户信息数据库操作 */
    @Autowired
    private TMemberInfoMapper memberInfoMapper;

    /**
     * 新增商户信息
     * @param memberInfo 商户信息
     * @return true 新增成功 false 新增失败
     */
    @SysLogId("数据库-新增商户信息")
    public boolean addMember(TMemberInfo memberInfo){
        TMemberInfo sel = memberInfoMapper.selectByMemberId(memberInfo.getMemberId());
        if(sel!=null){
            log.error("新增商户信息:{},已存在",memberInfo);
            throw new BusinessException(ErrorCodeEnum.DB_DATA_IS_EXITS.getCode(),ErrorCodeEnum.DB_DATA_IS_EXITS.getMsg());
        }
        int i = memberInfoMapper.insertSelective(memberInfo);
        if(i==1){
            return true;
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }

    /**
     * 根据商户ID查询商户信息
     * @param memberId 商户id
     * @return 商户信息
     */
    @SysLogId("数据库-查询商户信息")
    public TMemberInfo selectMember(String memberId){
        return memberInfoMapper.selectByMemberId(memberId);
    }

    /**
     * 根据id更新商户信息
     * @param memberInfo 商户信息
     * @return 更新结果 true 更新成功 false 更新失败
     */
    @SysLogId("数据库-更新商户信息")
    public boolean updateMember(TMemberInfo memberInfo){
        TMemberInfo sel = memberInfoMapper.selectByMemberId(memberInfo.getMemberId());
        if(sel!=null&&sel.getId().intValue()!=memberInfo.getId().intValue()){
            log.error("更新商户信息:{},商户号已存在,更新失败",memberInfo);
            throw new BusinessException(ErrorCodeEnum.DB_DATA_IS_EXITS.getCode(),ErrorCodeEnum.DB_DATA_IS_EXITS.getMsg());
        }
        int i = memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
        if(i==1){
            return true;
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }


}
