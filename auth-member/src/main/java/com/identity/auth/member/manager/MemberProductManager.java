package com.identity.auth.member.manager;

import com.identity.auth.common.annotation.SysLogId;
import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.dal.mapper.TMemberInfoMapper;
import com.identity.auth.dal.mapper.TMemberProductInfoMapper;
import com.identity.auth.dal.mapper.TProductInfoMapper;
import com.identity.auth.dal.model.TMemberInfo;
import com.identity.auth.dal.model.TMemberProductInfo;
import com.identity.auth.dal.model.TProductInfo;
import com.identity.auth.member.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商户开通产品管理
 * Created by lijing on 2017/10/26 0026.
 */
@Slf4j
@Component
public class MemberProductManager {

    /** 商户信息数据库操作 */
    @Autowired
    private TMemberInfoMapper memberInfoMapper;

    /** 产品信息数据库操作 */
    @Autowired
    private TProductInfoMapper productInfoMapper;

    /** 商户产品关系维护数据库操作 */
    @Autowired
    private TMemberProductInfoMapper memberProductInfoMapper;


    /**
     * 商户产品开通
     * @param memberProductInfo 商户产品开通信息
     * @return 商户产品开通结果 true 开通成功
     */
    @SysLogId("数据库-商户产品开通")
    public boolean openMemberProduct(TMemberProductInfo memberProductInfo){
        TMemberInfo memberInfo = memberInfoMapper.selectByMemberId(memberProductInfo.getMemberId());
        if(memberInfo==null|| !StatusEnum.NORMAL.getCode().equals(memberInfo.getMemberStatus())){
            log.info("商户产品开通信息:{},商户不存在，开通失败",memberProductInfo);
            throw new BusinessException(ErrorCodeEnum.MEMBER_NOT_EXITS.getCode(),ErrorCodeEnum.MEMBER_NOT_EXITS.getMsg());
        }
        TProductInfo productInfo = productInfoMapper.selectByProductId(memberProductInfo.getProductId());
        if(productInfo==null||!StatusEnum.NORMAL.getCode().equals(productInfo.getProductStatus())){
            log.info("商户产品开通信息:{},产品不存在，开通失败",memberProductInfo);
            throw new BusinessException(ErrorCodeEnum.PRODUCT_NOT_EXITS.getCode(),ErrorCodeEnum.PRODUCT_NOT_EXITS.getMsg());
        }
        TMemberProductInfo sel = memberProductInfoMapper.selectMemberProductById(memberProductInfo.getMemberId(),memberProductInfo.getProductId(),null);
        if(sel != null){
            log.info("商户产品开通信息:{},商户产品已存在，开通失败",memberProductInfo);
            throw new BusinessException(ErrorCodeEnum.MEMBER_PRODUCT_IS_EXITS.getCode(),ErrorCodeEnum.MEMBER_PRODUCT_IS_EXITS.getMsg());
        }
        int i = memberProductInfoMapper.insert(memberProductInfo);
        if(i==1){
            return true;
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }

    /**
     * 关闭商户产品
     * @param id 产品关闭id
     * @return 商户产品关闭结果 true 关闭成功
     */
    @SysLogId("数据库-商户产品关闭")
    public boolean closeMemberProduct(Integer id){
        TMemberProductInfo sel = memberProductInfoMapper.selectById(id);
        if(sel==null||!StatusEnum.NORMAL.getCode().equals(sel.getOpenStatus())){
            log.info("商户产品关闭信息:{},商户产品未开通，关闭失败",id);
            throw new BusinessException(ErrorCodeEnum.MEMBER_PRODUCT_NOT_EXITS.getCode(),ErrorCodeEnum.MEMBER_PRODUCT_NOT_EXITS.getMsg());
        }
        TMemberProductInfo update = new TMemberProductInfo();
        update.setId(sel.getId());
        update.setOpenStatus(StatusEnum.DISABLE.getCode());
        int i = memberProductInfoMapper.updateByPrimaryKeySelective(update);
        if(i==1){
            return true;
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }

    /**
     * 更新商户产品
     * @param memberProductInfo 需要更新的商户产品信息(含id)
     * @return 商户产品更新结果 true 更新成功
     */
    @SysLogId("数据库-商户产品更新")
    public boolean updateMemberProduct(TMemberProductInfo memberProductInfo){
        TMemberProductInfo sel = memberProductInfoMapper.selectMemberProductById(memberProductInfo.getMemberId(),memberProductInfo.getProductId(),null);
        if(sel != null&&sel.getId().intValue()!=memberProductInfo.getId().intValue()){
            log.info("商户产品更新信息:{},商户产品已存在，更新失败",memberProductInfo);
            throw new BusinessException(ErrorCodeEnum.MEMBER_PRODUCT_IS_EXITS.getCode(),ErrorCodeEnum.MEMBER_PRODUCT_IS_EXITS.getMsg());
        }
        int i = memberProductInfoMapper.updateByPrimaryKeySelective(memberProductInfo);
        if(i==1){
            return true;
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }

    /**
     * 查询商户所有产品
     * @param memberId 商户id
     * @param openStatus 开通状态(可以为空)
     * @return 商户产品信息
     */
    @SysLogId("数据库-查询商户所有产品")
    public List<TMemberProductInfo> selectAllMemberProduct(String memberId,String openStatus){
        return memberProductInfoMapper.selectByMemberId(memberId, openStatus);
    }

    /**
     * 检查并查询商户产品信息
     * @param memberId 商户id
     * @param productId 产品id(可为空)
     * @return 商户已开通的可用产品
     */
    @SysLogId("数据库-检查并查询商户所有产品")
    public List<TMemberProductInfo> selectAndCheckMemberProduct(String memberId,String productId){
        return memberProductInfoMapper.selectMemberOpenProduct(memberId,StatusEnum.NORMAL.getCode(),productId);
    }
}
