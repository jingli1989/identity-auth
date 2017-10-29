package com.identity.auth.web.controller;

import com.identity.auth.common.util.BeanMapperUtil;
import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.model.req.IdentityAuthReqDTO;
import com.identity.auth.model.res.IdentityAuthResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 身份认证服务
 * Created by Administrator on 2017/10/29 0029.
 */
@Slf4j
@RestController
@RequestMapping(value = "/identity")
public class IdentityAuthController {

    /**
     * 身份认证
     * @param reqDTO 认证请求信息
     * @return 认证结果
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public IdentityAuthResult<IdentityAuthResDTO> auth(@RequestBody IdentityAuthReqDTO reqDTO){
        log.info("身份认证，请求:{}",reqDTO);
        IdentityAuthResDTO resDTO = BeanMapperUtil.objConvert(reqDTO,IdentityAuthResDTO.class);
        IdentityAuthResult<IdentityAuthResDTO> result = new IdentityAuthResult<>(resDTO);
        log.info("身份认证，响应:{}",result);
        return result;
    }
}
