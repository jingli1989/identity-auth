package com.identity.auth.dal.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** 
 * 商户开通产品信息 t_member_product_info
 * @author lijing
 * date:2017/10/26 09:17
 */
@Data
public class TMemberProductInfo implements Serializable {
    /** 串行版本ID*/
    private static final long serialVersionUID = 338404716095044223L;

    /** 编号 */
    private Integer id;

    /** 商户id */
    private String memberId;

    /** 产品id */
    private String productId;

    /** 开通状态(NORMAL 正常 DISABLE 禁用 ) */
    private String openStatus;

    /** 创建时间  默认：CURRENT_TIMESTAMP */
    private Date createdAt;

    /** 创建人 */
    private String createdBy;

    /** 最后更新时间 */
    private Date updatedAt;

    /** 最后更新人 */
    private String updatedBy;
}