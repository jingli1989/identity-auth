package com.identity.auth.dal.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** 
 * 渠道请求订单 t_business_order_info
 * @author lijing
 * date:2017/10/26 09:17
 */
@Data
public class TBusinessOrderInfo implements Serializable {
    /** 串行版本ID*/
    private static final long serialVersionUID = -917757308298258595L;

    /** 编号 */
    private Integer id;

    /** 业务流水 */
    private String businessNo;

    /** 系统流水 */
    private String tradeNo;

    /** 渠道编号 */
    private String channelId;

    /** 渠道计费标识(Y 收费 N 不收费) */
    private String feeFlag;

    /** 渠道耗时(ms) */
    private Integer channelTime;

    /** 渠道请求流水 */
    private String reqNo;

    /** 渠道响应代码 */
    private String resCode;

    /** 渠道响应描述 */
    private String resMsg;

    /** 渠道响应流水 */
    private String resNo;

    /** 创建时间  默认：CURRENT_TIMESTAMP */
    private Date createdAt;

    /** 创建人 */
    private String createdBy;

    /** 最后更新时间 */
    private Date updatedAt;

    /** 最后更新人 */
    private String updatedBy;
}