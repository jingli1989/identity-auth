package com.identity.auth.service;

import com.identity.auth.dal.model.PageDTO;
import com.identity.auth.service.manager.OrderQueryManager;
import com.identity.auth.service.model.req.ChannelOrderQueryReqDTO;
import com.identity.auth.service.model.req.OrderCountReqDTO;
import com.identity.auth.service.model.req.OrderQueryReqDTO;
import com.identity.auth.service.model.res.ChannelOrderQueryResDTO;
import com.identity.auth.service.model.res.OrderCountResDTO;
import com.identity.auth.service.model.res.OrderQueryResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单查询服务
 * Created by lijing on 2017/11/14 0014.
 */
@Slf4j
@Service
public class OrderQueryService {
    @Autowired
    private OrderQueryManager orderQueryManager;

    /**
     * 分页查询订单信息
     * @param reqDTO 查询请求
     * @param pageDTO 分页信息
     * @return 查询结果
     */
    public PageDTO<OrderQueryResDTO> orderPage(OrderQueryReqDTO reqDTO, PageDTO<OrderQueryResDTO> pageDTO){
        return orderQueryManager.queryOrderByPage(reqDTO, pageDTO);
    }

    /**
     * 分页查询渠道订单信息
     * @param reqDTO 查询请求
     * @param pageDTO 分页信息
     * @return 查询结果
     */
    public PageDTO<ChannelOrderQueryResDTO> channelPage(ChannelOrderQueryReqDTO reqDTO, PageDTO<ChannelOrderQueryResDTO> pageDTO){
        return orderQueryManager.queryChannelByPage(reqDTO, pageDTO);
    }

    /**
     * 订单信息统计
     * @param reqDTO 请求
     * @return 查询结果
     */
    public List<OrderCountResDTO> orderCount(OrderCountReqDTO reqDTO){
        return orderQueryManager.countOrder(reqDTO);
    }

    /**
     * 订单信息统计
     * @param reqDTO 请求
     * @return 查询结果
     */
    public List<OrderCountResDTO> channelCount(OrderCountReqDTO reqDTO){
        return orderQueryManager.countChannel(reqDTO);
    }
}
