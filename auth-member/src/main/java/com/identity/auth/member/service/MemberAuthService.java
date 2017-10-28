package com.identity.auth.member.service;

import com.identity.auth.common.annotation.SysLogId;
import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.common.util.BeanMapperUtil;
import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.common.util.LogUtil;
import com.identity.auth.dal.model.TMemberInfo;
import com.identity.auth.dal.model.TMemberProductInfo;
import com.identity.auth.member.api.MemberAuthFacade;
import com.identity.auth.member.enums.StatusEnum;
import com.identity.auth.member.manager.MemberManager;
import com.identity.auth.member.manager.MemberProductManager;
import com.identity.auth.member.model.MemberInfoResDTO;
import com.identity.auth.member.model.MemberProductCheckResDTO;
import com.identity.auth.member.model.ProductInfoResDTO;
import com.identity.auth.member.util.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商户鉴权认证服务
 * Created by lijing on 2017/10/26 0026.
 */
@Slf4j
@Service
public class MemberAuthService implements MemberAuthFacade {

    /** 商户管理 */
    private final MemberManager memberManager;

    /** 商户产品管理 */
    private final MemberProductManager memberProductManager;

    @Autowired
    public MemberAuthService(MemberManager memberManager, MemberProductManager memberProductManager) {
        this.memberManager = memberManager;
        this.memberProductManager = memberProductManager;
    }

    /**
     * 商户校验并获取商户信息
     *
     * @param memberId 商户id
     * @param logId 日志id
     * @return 认证结果
     */
    @SysLogId("商户鉴权")
    @Override
    public IdentityAuthResult<MemberInfoResDTO> memberCheck(String memberId,String logId) {
        try {
            LogUtil.updateLogId(logId);
            log.info("商户:{},进行状态校验及信息查询",memberId);
            //查询缓存
            MemberInfoResDTO resDTO = CacheUtil.getMemberInfo(memberId);
            if(resDTO!=null){
                return new IdentityAuthResult<>(resDTO);
            }
            TMemberInfo memberInfo = memberManager.selectMember(memberId);
            if(memberId==null|| !StatusEnum.NORMAL.getCode().equals(memberInfo.getMemberStatus())){
                return new IdentityAuthResult<>(ErrorCodeEnum.MEMBER_NOT_EXITS.getCode(),ErrorCodeEnum.MEMBER_NOT_EXITS.getMsg());
            }
            resDTO = BeanMapperUtil.objConvert(memberInfo,MemberInfoResDTO.class);
            //放入缓存
            CacheUtil.cacheMemberInfo(resDTO);
            return new IdentityAuthResult<>(resDTO);
        }catch (BusinessException be){
            return new IdentityAuthResult<>(be.getCode(),be.getResMessage());
        }catch (Exception e){
            log.error("商户鉴权失败，原因:",e);
            return new IdentityAuthResult<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    /**
     * 商户产品权限校验
     *
     * @param memberId  商户ID
     * @param productId 产品ID
     * @param logId 日志id
     * @return 校验结果
     */
    @SysLogId("商户产品鉴权")
    @Override
    public IdentityAuthResult<ProductInfoResDTO> productCheck(String memberId, String productId,String logId) {
        try {
            LogUtil.updateLogId(logId);
            log.info("商户:{},进行商户产品校验",memberId);
            List<TMemberProductInfo> memberProductInfoList = memberProductManager.selectAndCheckMemberProduct(memberId,productId);
            if(memberProductInfoList==null||memberProductInfoList.isEmpty()){
                return new IdentityAuthResult<>(ErrorCodeEnum.MEMBER_PRODUCT_NOT_EXITS.getCode(),ErrorCodeEnum.MEMBER_PRODUCT_NOT_EXITS.getMsg());
            }
            TMemberProductInfo productInfo = memberProductInfoList.get(0);
            ProductInfoResDTO resDTO = BeanMapperUtil.objConvert(productInfo,ProductInfoResDTO.class);
            return new IdentityAuthResult<>(resDTO);
        }catch (BusinessException be){
            return new IdentityAuthResult<>(be.getCode(),be.getResMessage());
        }catch (Exception e){
            log.error("商户产品校验失败，原因:",e);
            return new IdentityAuthResult<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    /**
     * 商户产品权限校验(二合一)
     *
     * @param memberId  商户ID
     * @param productId 产品ID
     * @param logId     日志id
     * @return 校验结果
     */
    @SysLogId("商户+产品鉴权")
    @Override
    public IdentityAuthResult<MemberProductCheckResDTO> memberAuth(String memberId, String productId, String logId) {
        try {
            LogUtil.updateLogId(logId);
            log.info("商户:{},进行商户产品校验",memberId);
            //查询缓存
            MemberProductCheckResDTO resDTO = CacheUtil.getMemberProduct(memberId, productId);
            if(resDTO!=null){
                return new IdentityAuthResult<>(resDTO);
            }
            //商户校验
            TMemberInfo memberInfo = memberManager.selectMember(memberId);
            if(memberId==null|| !StatusEnum.NORMAL.getCode().equals(memberInfo.getMemberStatus())){
                return new IdentityAuthResult<>(ErrorCodeEnum.MEMBER_NOT_EXITS.getCode(),ErrorCodeEnum.MEMBER_NOT_EXITS.getMsg());
            }
            //商户产品校验
            List<TMemberProductInfo> memberProductInfoList = memberProductManager.selectAndCheckMemberProduct(memberId,productId);
            if(memberProductInfoList==null||memberProductInfoList.isEmpty()){
                return new IdentityAuthResult<>(ErrorCodeEnum.MEMBER_PRODUCT_NOT_EXITS.getCode(),ErrorCodeEnum.MEMBER_PRODUCT_NOT_EXITS.getMsg());
            }
            TMemberProductInfo productInfo = memberProductInfoList.get(0);
            resDTO = new MemberProductCheckResDTO();
            BeanMapperUtil.copy(productInfo,resDTO);
            BeanMapperUtil.copy(memberInfo,resDTO);
            //放入缓存
            CacheUtil.cacheMemberProduct(resDTO);
            return new IdentityAuthResult<>(resDTO);
        }catch (BusinessException be){
            return new IdentityAuthResult<>(be.getCode(),be.getResMessage());
        }catch (Exception e){
            log.error("商户产品校验失败，原因:",e);
            return new IdentityAuthResult<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }
}
