package com.identity.auth.dal.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** 
 * 商户信息表 t_member_info
 * @author lijing
 * date:2017/10/26 09:17
 */
@Data
public class TMemberInfo implements Serializable {
    /** 串行版本ID*/
    private static final long serialVersionUID = 8354504344414515725L;

    /** 编号 */
    private Integer id;

    /** 商户编号 */
    private String memberId;

    /** 商户全称 */
    private String memberName;

    /** 商户简称 */
    private String shortName;

    /** 商户状态(NORMAL 正常 DISABLE 不可用) */
    private String memberStatus;

    /** 商户加密key */
    private String securityKey;

    /** 联系人 */
    private String contacts;

    /** 座机 */
    private String telNumber;

    /** 联系人手机号码 */
    private String phoneNum;

    /** 公司所在城市 */
    private String cityName;

    /** 公司地址 */
    private String companyAddr;

    /** 创建时间  默认：CURRENT_TIMESTAMP */
    private Date createdAt;

    /** 创建人 */
    private String createdBy;

    /** 最后更新时间 */
    private Date updatedAt;

    /** 最后更新人 */
    private String updatedBy;
}