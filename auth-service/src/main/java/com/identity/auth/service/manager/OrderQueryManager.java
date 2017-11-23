package com.identity.auth.service.manager;

import com.identity.auth.common.util.BeanMapperUtil;
import com.identity.auth.dal.mapper.OrderCountMapper;
import com.identity.auth.dal.mapper.TBusinessOrderInfoMapper;
import com.identity.auth.dal.mapper.TOrderInfoMapper;
import com.identity.auth.dal.model.*;
import com.identity.auth.service.model.req.ChannelOrderQueryReqDTO;
import com.identity.auth.service.model.req.OrderCountReqDTO;
import com.identity.auth.service.model.req.OrderQueryReqDTO;
import com.identity.auth.service.model.res.ChannelOrderQueryResDTO;
import com.identity.auth.service.model.res.OrderCountResDTO;
import com.identity.auth.service.model.res.OrderQueryResDTO;
import com.identity.auth.service.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单查询
 * Created by lijing on 2017/11/14 0014.
 */
@Slf4j
@Component
public class OrderQueryManager {
    /** 订单数据库操作 */
    @Autowired(required = false)
    private TOrderInfoMapper orderInfoMapper;
    /** 业务流水数据库操作 */
    @Autowired(required = false)
    private TBusinessOrderInfoMapper businessOrderInfoMapper;

    @Autowired(required = false)
    private OrderCountMapper orderCountMapper;

    /**
     * 分页查询订单信息
     * @param reqDTO 查询请求
     * @param pageDTO 分页参数
     * @return 查询结果
     */
    public PageDTO<OrderQueryResDTO> queryOrderByPage(OrderQueryReqDTO reqDTO,PageDTO<OrderQueryResDTO> pageDTO){
        TOrderInfo orderInfo = BeanMapperUtil.objConvert(reqDTO,TOrderInfo.class);
        QueryTimeDTO queryTimeDTO = new QueryTimeDTO();
        queryTimeDTO.setStartTime(DateUtil.parse(reqDTO.getStartTime(),DateUtil.YYYYMMDDHHmmSS));
        queryTimeDTO.setEndTime(DateUtil.parse(reqDTO.getEndTime(),DateUtil.YYYYMMDDHHmmSS));
        int count = orderInfoMapper.selectCount(orderInfo,queryTimeDTO);
        pageDTO.setCount(count);
        if(count==0){
            pageDTO.setPage(1);
            return pageDTO;
        }
        pageDTO.checkPage();
        //处理最大页数
        int maxPage = count/ pageDTO.getPageNum()+1;
        if(pageDTO.getPage()>maxPage){
            pageDTO.setPage(maxPage);
        }
        PageDTO queryPage = new PageDTO();
        queryPage.setPage(pageDTO.getPage()-1);
        queryPage.setPageNum(pageDTO.getPageNum());
        List<TOrderInfo> result = orderInfoMapper.selectByPage(orderInfo,queryTimeDTO,queryPage);
        if(result==null||result.isEmpty()){
            return pageDTO;
        }
        List<OrderQueryResDTO> resList = BeanMapperUtil.mapList(result,OrderQueryResDTO.class);
        pageDTO.setResult(resList);
        return pageDTO;
    }

    /**
     * 分页查询订单信息
     * @param reqDTO 查询请求
     * @param pageDTO 分页参数
     * @return 查询结果
     */
    public PageDTO<ChannelOrderQueryResDTO> queryChannelByPage(ChannelOrderQueryReqDTO reqDTO, PageDTO<ChannelOrderQueryResDTO> pageDTO){
        TBusinessOrderInfo orderInfo = BeanMapperUtil.objConvert(reqDTO,TBusinessOrderInfo.class);
        QueryTimeDTO queryTimeDTO = new QueryTimeDTO();
        queryTimeDTO.setStartTime(DateUtil.parse(reqDTO.getStartTime(),DateUtil.YYYYMMDDHHmmSS));
        queryTimeDTO.setEndTime(DateUtil.parse(reqDTO.getEndTime(),DateUtil.YYYYMMDDHHmmSS));
        int count = businessOrderInfoMapper.selectCount(orderInfo,queryTimeDTO);
        pageDTO.setCount(count);
        if(count==0){
            pageDTO.setPage(1);
            return pageDTO;
        }
        pageDTO.checkPage();
        //处理最大页数
        int maxPage = count/ pageDTO.getPageNum()+1;
        if(pageDTO.getPage()>maxPage){
            pageDTO.setPage(maxPage);
        }
        PageDTO queryPage = new PageDTO();
        queryPage.setPage(pageDTO.getPage()-1);
        queryPage.setPageNum(pageDTO.getPageNum());
        List<TBusinessOrderInfo> result = businessOrderInfoMapper.selectByPage(orderInfo,queryTimeDTO,queryPage);
        if(result==null||result.isEmpty()){
            return pageDTO;
        }
        List<ChannelOrderQueryResDTO> resList = BeanMapperUtil.mapList(result,ChannelOrderQueryResDTO.class);
        pageDTO.setResult(resList);
        return pageDTO;
    }

    /**
     * 统计订单信息
     * @param reqDTO 统计条件
     * @return 统计结果
     */
    public List<OrderCountResDTO> countOrder(OrderCountReqDTO reqDTO){
        TOrderInfo orderInfo = BeanMapperUtil.objConvert(reqDTO,TOrderInfo.class);
        QueryTimeDTO queryTimeDTO = new QueryTimeDTO();
        queryTimeDTO.setStartTime(DateUtil.parse(reqDTO.getStartTime(),DateUtil.YYYYMMDDHHmmSS));
        queryTimeDTO.setEndTime(DateUtil.parse(reqDTO.getEndTime(),DateUtil.YYYYMMDDHHmmSS));
        List<OrderCountDTO> countList = orderCountMapper.countOrder(orderInfo,queryTimeDTO);
        if(countList==null||countList.isEmpty()){
            return new ArrayList<>();
        }
        return BeanMapperUtil.mapList(countList,OrderCountResDTO.class);
    }
    /**
     * 统计渠道订单信息
     * @param reqDTO 统计条件
     * @return 统计结果
     */
    public List<OrderCountResDTO> countChannel(OrderCountReqDTO reqDTO){
        TBusinessOrderInfo orderInfo = BeanMapperUtil.objConvert(reqDTO,TBusinessOrderInfo.class);
        QueryTimeDTO queryTimeDTO = new QueryTimeDTO();
        queryTimeDTO.setStartTime(DateUtil.parse(reqDTO.getStartTime(),DateUtil.YYYYMMDDHHmmSS));
        queryTimeDTO.setEndTime(DateUtil.parse(reqDTO.getEndTime(),DateUtil.YYYYMMDDHHmmSS));
        List<OrderCountDTO> countList = orderCountMapper.countChannel(orderInfo,queryTimeDTO);
        if(countList==null||countList.isEmpty()){
            return new ArrayList<>();
        }
        return BeanMapperUtil.mapList(countList,OrderCountResDTO.class);
    }
}
