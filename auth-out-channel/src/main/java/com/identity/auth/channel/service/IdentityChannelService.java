package com.identity.auth.channel.service;

import com.identity.auth.channel.api.IdentityChannelFacade;
import com.identity.auth.channel.channel.IdentityAbstract;
import com.identity.auth.channel.model.IdentityReqDTO;
import com.identity.auth.channel.model.IdentityResDTO;
import com.identity.auth.common.annotation.SysLogId;
import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.common.util.IdentityAuthResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 身份认证渠道服务
 * Created by lijing on 2017/11/9 0009.
 */
@Slf4j
@Service
public class IdentityChannelService implements IdentityChannelFacade {
    /** 身份认证渠道服务 */
    @Autowired
    private List<IdentityAbstract> list;
    /** 身份认证渠道Map */
    private Map<String,IdentityAbstract> identityMap;

    @PostConstruct
    public void init(){
        identityMap = new HashMap<>();
        if(list==null||list.isEmpty()){
            log.error("当前无可用渠道信息");
            return;
        }
        for (IdentityAbstract identityAbstract : list){
            if(identityAbstract==null|| StringUtils.isBlank(identityAbstract.getChannelId())){
                log.error("渠道信息:{},无法获取渠道id，加载失败",identityAbstract);
                continue;
            }
            identityMap.put(identityAbstract.getChannelId(),identityAbstract);
            log.info("成功加载渠道:{}",identityAbstract.getChannelId());
        }
        log.info("成功加载身份证认证渠道:{}个",identityMap.size());
    }


    /**
     * 身份认证
     *
     * @param reqDTO 身份认证请求
     * @return 身份认证响应
     */
    @SysLogId("身份认证渠道处理")
    @Override
    public IdentityAuthResult<IdentityResDTO> identityAuth(IdentityReqDTO reqDTO) {
        try {

            IdentityAbstract identityAbstract = identityMap.get(reqDTO.getChannelId());
            if(identityAbstract==null){
                log.error("根据渠道id:{},获取到渠道实现类为空，无法进行认证服务",reqDTO.getChannelId());
                return new IdentityAuthResult<>(ErrorCodeEnum.CHANNEL_NOT_EXITS.getCode(),ErrorCodeEnum.CHANNEL_NOT_EXITS.getMsg());
            }
            IdentityResDTO identityResDTO = identityAbstract.identityAuth(reqDTO);
            return new IdentityAuthResult<>(identityResDTO);
        }catch (BusinessException be){
            return new IdentityAuthResult<>(be.getCode(),be.getResMessage());
        }catch (Exception e){
            log.error("渠道处理失败，原因:",e);
            return new IdentityAuthResult<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }

    }
}
