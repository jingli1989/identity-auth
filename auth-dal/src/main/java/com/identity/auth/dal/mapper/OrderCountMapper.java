package com.identity.auth.dal.mapper;

import com.identity.auth.dal.model.OrderCountDTO;
import com.identity.auth.dal.model.QueryTimeDTO;
import com.identity.auth.dal.model.TBusinessOrderInfo;
import com.identity.auth.dal.model.TOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单统计mapper
 * Created by lijing on 2017/11/15 0015.
 */
public interface OrderCountMapper {
    /**
     * 统计商户订单
     * @param record 统计条件
     * @param queryTimeDTO 查询时间条件
     * @return 统计结果
     */
    List<OrderCountDTO> countOrder(@Param(value = "query")TOrderInfo record, @Param(value = "queryTime")QueryTimeDTO queryTimeDTO);

    /**
     * 统计渠道订单
     * @param record 统计条件
     * @param queryTimeDTO 查询时间条件
     * @return 统计结果
     */
    List<OrderCountDTO> countChannel(@Param(value = "query")TBusinessOrderInfo record, @Param(value = "queryTime")QueryTimeDTO queryTimeDTO);
}
