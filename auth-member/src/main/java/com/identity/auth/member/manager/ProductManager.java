package com.identity.auth.member.manager;

import com.identity.auth.common.annotation.SysLogId;
import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.dal.mapper.TProductInfoMapper;
import com.identity.auth.dal.model.TProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 产品管理
 * Created by lijing on 2017/10/26 0026.
 */
@Slf4j
@Component
public class ProductManager {

    /** 产品信息数据库操作 */
    @Autowired
    private TProductInfoMapper productInfoMapper;

    /**
     * 新增产品信息
     * @param productInfo 需要新增的产品信息
     * @return 新增结果 true 新增成功 false 新增失败
     */
    @SysLogId("数据库-新增产品信息")
    public boolean addProduct(TProductInfo productInfo){
        TProductInfo sel = productInfoMapper.selectByProductId(productInfo.getProductId());
        if(sel!=null){
            log.error("新增产品信息:{},已存在",productInfo);
            throw new BusinessException(ErrorCodeEnum.DB_DATA_IS_EXITS.getCode(),ErrorCodeEnum.DB_DATA_IS_EXITS.getMsg());
        }
        int i =  productInfoMapper.insert(productInfo);
        if(i==1){
            return true;
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());

    }

    /**
     * 根据产品ID查询产品信息
     * @param productId 产品id
     * @return 产品信息
     */
    @SysLogId("数据库-查询产品信息")
    public TProductInfo selectProduct(String productId){
        return productInfoMapper.selectByProductId(productId);
    }

    /**
     * 根据id更新产品信息
     * @param productInfo 产品信息
     * @return 更新结果 true 更新成功 false 更新失败
     */
    @SysLogId("数据库-更新产品信息")
    public boolean updateMember(TProductInfo productInfo){
        TProductInfo sel = productInfoMapper.selectByProductId(productInfo.getProductId());
        if(sel!=null&&sel.getId().intValue()!=productInfo.getId().intValue()){
            log.error("更新产品信息:{},产品ID已存在,更新失败",productInfo);
            throw new BusinessException(ErrorCodeEnum.DB_DATA_IS_EXITS.getCode(),ErrorCodeEnum.DB_DATA_IS_EXITS.getMsg());
        }
        int i = productInfoMapper.updateByPrimaryKeySelective(productInfo);
        if(i==1){
            return true;
        }
        throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
    }



}
