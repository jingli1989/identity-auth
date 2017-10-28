package com.identity.auth.dal.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** 
 * 产品渠道开通信息 t_product_channel_info
 * @author lijing
 * date:2017/10/26 09:17
 */
@Data
public class TProductChannelInfo implements Serializable {
    /** 串行版本ID*/
    private static final long serialVersionUID = -5179763072675895308L;

    /** 编号 */
    private Integer id;

    /** 渠道id */
    private String channelId;

    /** 产品id */
    private String productId;

    /** 开通状态(NORMAL 正常 DISABLE 禁用) */
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