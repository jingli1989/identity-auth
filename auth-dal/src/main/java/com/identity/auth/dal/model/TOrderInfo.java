package com.identity.auth.dal.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** 
 * 订单表 t_order_info
 * @author lijing
 * date:2017/10/26 09:17
 */
@Data
public class TOrderInfo implements Serializable {
    /** 串行版本ID*/
    private static final long serialVersionUID = -1743261185189994657L;

    /** 编号 */
    private Integer id;

    /** 商户号 */
    private String memberId;

    /** 商户订单号 */
    private String memberTransId;

    /** 商户订单时间  默认：CURRENT_TIMESTAMP */
    private Date memberTransDate;

    /** 产品id */
    private String productId;

    /**  订单金额 */
    private Integer orderMoney;

    /** 系统流水 */
    private String tradeNo;

    /** 业务流水 */
    private String businessNo;

    /** 订单状态(INIT 初始 FAIL 失败 SUCCESS 成功) */
    private String orderStatus;

    /** 收费标识 (Y 收费 N 不收费 ) */
    private String feeFlag;

    /** 响应编码 */
    private String resCode;

    /** 响应描述 */
    private String resMsg;

    /** 创建时间  默认：0000-00-00 00:00:00 */
    private Date createdAt;

    /** 创建人 */
    private String createdBy;

    /** 最后更新时间 */
    private Date updatedAt;

    /** 最后更新人 */
    private String updatedBy;
}