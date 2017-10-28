package com.identity.auth.dal.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** 
 * 渠道信息表 t_channel_info
 * @author lijing
 * date:2017/10/26 09:17
 */
@Data
public class TChannelInfo implements Serializable {
    /** 串行版本ID*/
    private static final long serialVersionUID = -2775698695368318403L;

    /** 编号 */
    private Integer id;

    /** 渠道编号 */
    private String channelId;

    /** 渠道名称 */
    private String channelName;

    /** 渠道状态(NORMAL 正常 DISABLE 禁用) */
    private String channelStatus;

    /** 创建时间  默认：CURRENT_TIMESTAMP */
    private Date createdAt;

    /** 创建人 */
    private String createdBy;

    /** 最后更新时间 */
    private Date updatedAt;

    /** 最后更新人 */
    private String updatedBy;
}