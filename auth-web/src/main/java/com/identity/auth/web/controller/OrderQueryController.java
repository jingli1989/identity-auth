package com.identity.auth.web.controller;

import com.identity.auth.common.annotation.SysLogId;
import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.dal.model.PageDTO;
import com.identity.auth.service.OrderQueryService;
import com.identity.auth.service.model.req.ChannelOrderQueryReqDTO;
import com.identity.auth.service.model.req.OrderCountReqDTO;
import com.identity.auth.service.model.req.OrderQueryReqDTO;
import com.identity.auth.service.model.res.ChannelOrderQueryResDTO;
import com.identity.auth.service.model.res.OrderCountResDTO;
import com.identity.auth.service.model.res.OrderQueryResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单查询
 * Created by lijing on 2017/11/14 0014.
 */
@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderQueryController {

    @Autowired
    private OrderQueryService orderQueryService;


    /**
     * 分页查询商户订单信息
     * @param reqDTO 请求信息
     * @param page 页码
     * @param pageNum 每页记录数
     * @return 查询结果
     */
    @SysLogId("控制层-分页查询订单信息")
    @RequestMapping(value = "/order/page/{page}/{page_num}", method = RequestMethod.POST)
    public IdentityAuthResult<PageDTO<OrderQueryResDTO>> orderPage(@RequestBody OrderQueryReqDTO reqDTO,
                                                                   @PathVariable("page")Integer page,
                                                                   @PathVariable("page_num")Integer pageNum){
        try {
            PageDTO<OrderQueryResDTO> pageDTO = new PageDTO<>();
            pageDTO.setPage(page);
            pageDTO.setPageNum(pageNum);
            return new IdentityAuthResult<>(orderQueryService.orderPage(reqDTO, pageDTO));
        }catch (BusinessException be){
            return new IdentityAuthResult<>(be.getCode(),be.getResMessage());
        }catch (Exception e){
            return new IdentityAuthResult<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    /**
     * 分页查询渠道订单信息
     * @param reqDTO 请求信息
     * @param page 页码
     * @param pageNum 每页记录数
     * @return 查询结果
     */
    @SysLogId("控制层-分页查询渠道订单信息")
    @RequestMapping(value = "/channel/page/{page}/{page_num}", method = RequestMethod.POST)
    public IdentityAuthResult<PageDTO<ChannelOrderQueryResDTO>> channelPage(@RequestBody ChannelOrderQueryReqDTO reqDTO,
                                                        @PathVariable("page")Integer page,
                                                        @PathVariable("page_num")Integer pageNum){
        try {
            PageDTO<ChannelOrderQueryResDTO> pageDTO = new PageDTO<>();
            pageDTO.setPage(page);
            pageDTO.setPageNum(pageNum);
            return new IdentityAuthResult<>(orderQueryService.channelPage(reqDTO, pageDTO));
        }catch (BusinessException be){
            return new IdentityAuthResult<>(be.getCode(),be.getResMessage());
        }catch (Exception e){
            return new IdentityAuthResult<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    /**
     * 订单信息统计
     * @param reqDTO 请求
     * @return 查询结果
     */
    @SysLogId("控制层-商户订单统计")
    @RequestMapping(value = "/order/count", method = RequestMethod.POST)
    public IdentityAuthResult<List<OrderCountResDTO>> orderCount(@RequestBody OrderCountReqDTO reqDTO){
        try {
            return new IdentityAuthResult<>(orderQueryService.orderCount(reqDTO));
        }catch (BusinessException be){
            return new IdentityAuthResult<>(be.getCode(),be.getResMessage());
        }catch (Exception e){
            return new IdentityAuthResult<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

    /**
     * 订单信息统计
     * @param reqDTO 请求
     * @return 查询结果
     */
    @SysLogId("控制层-渠道订单统计")
    @RequestMapping(value = "/channel/count", method = RequestMethod.POST)
    public IdentityAuthResult<List<OrderCountResDTO>> channelCount(@RequestBody OrderCountReqDTO reqDTO){

        try {
            return new IdentityAuthResult<>( orderQueryService.channelCount(reqDTO));
        }catch (BusinessException be){
            return new IdentityAuthResult<>(be.getCode(),be.getResMessage());
        }catch (Exception e){
            return new IdentityAuthResult<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
    }

}
