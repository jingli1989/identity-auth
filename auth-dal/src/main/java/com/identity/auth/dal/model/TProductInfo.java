package com.identity.auth.dal.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** 
 * 产品信息表 t_product_info
 * @author lijing
 * date:2017/10/26 09:17
 */
@Data
public class TProductInfo implements Serializable {
    /** 串行版本ID*/
    private static final long serialVersionUID = 8945141929629541921L;

    /** 编号 */
    private Integer id;

    /** 产品编号 */
    private String productId;

    /** 产品状态(NORMAL 正常 DISABLE 禁用 ) */
    private String productStatus;

    /** 产品名称 */
    private String productName;

    /** 产品单价(单位:分) */
    private Integer unitPrice;

    /** 创建时间  默认：CURRENT_TIMESTAMP */
    private Date createdAt;

    /** 创建人 */
    private String createdBy;

    /** 最后更新时间 */
    private Date updatedAt;

    /** 最后更新人 */
    private String updatedBy;
}