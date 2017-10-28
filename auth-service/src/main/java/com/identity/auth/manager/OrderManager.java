package com.identity.auth.manager;

import com.identity.auth.dal.mapper.TOrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单信息管理
 * Created by lijing on 2017/10/26 0026.
 */
@Slf4j
@Component
public class OrderManager {

    /** 订单数据库操作 */
    @Autowired(required = false)
    private TOrderInfoMapper orderInfoMapper;

    public void createOrder(){

    }

}
