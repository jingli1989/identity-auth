package com.identity.auth.member.api;

import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.member.model.MemberInfoResDTO;
import com.identity.auth.member.model.MemberProductCheckResDTO;
import com.identity.auth.member.model.ProductInfoResDTO;

/**
 * 商户认证
 * Created by lijing on 2017/10/26 0026.
 */
public interface MemberAuthFacade {

    /**
     * 商户校验并获取商户信息
     * @param memberId 商户id
     * @param logId 日志id
     * @return 认证结果
     */
    IdentityAuthResult<MemberInfoResDTO> memberCheck(String memberId,String logId);

    /**
     * 商户产品权限校验
     * @param memberId 商户ID
     * @param productId 产品ID
     * @param logId 日志id
     * @return 校验结果
     */
    IdentityAuthResult<ProductInfoResDTO> productCheck(String memberId,String productId,String logId);

    /**
     * 商户产品权限校验(二合一)
     * @param memberId 商户ID
     * @param productId 产品ID
     * @param logId 日志id
     * @return 校验结果
     */
    IdentityAuthResult<MemberProductCheckResDTO> memberAuth(String memberId, String productId, String logId);
}
