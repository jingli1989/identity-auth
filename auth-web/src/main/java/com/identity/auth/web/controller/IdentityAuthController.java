package com.identity.auth.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.identity.auth.common.annotation.SysLogId;
import com.identity.auth.service.IdentityAuthService;
import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import com.identity.auth.common.util.HMacSHAUtil;
import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.service.model.HeaderAuthDTO;
import com.identity.auth.service.model.req.IdentityAuthReqDTO;
import com.identity.auth.service.model.res.IdentityAuthResDTO;
import com.identity.auth.web.util.HeaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 身份认证服务
 * Created by Administrator on 2017/10/29 0029.
 */
@Slf4j
@RestController
@RequestMapping(value = "/identity")
public class IdentityAuthController {

    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired(required = false)
    private HttpServletResponse response;
    @Autowired(required = false)
    private IdentityAuthService identityAuthService;

//    JSON序列化工具
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 身份认证
     * @param body 认证请求信息
     * @return 认证结果
     */
    @SysLogId("控制层-身份认证")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String auth(@RequestBody String body){
        IdentityAuthResult<IdentityAuthResDTO> result;
        String securityKey = null;
        try {
            log.info("身份认证，请求:{}",body);
            HeaderAuthDTO authDTO = HeaderUtil.getHeaderReq(request);
            log.info("认证请求密文:{}",authDTO);

            IdentityAuthReqDTO reqDTO = objectMapper.readValue(body,IdentityAuthReqDTO.class);
            securityKey = identityAuthService.getMemberSecurityKey(reqDTO.getMemberId());
            boolean flag = HMacSHAUtil.HMacSHA256Check(body,authDTO.getAuthCode(),securityKey);
            if(!flag){
                result = new IdentityAuthResult<>(ErrorCodeEnum.PARAMS_ERROR.getCode(),"请求信息校验失败");
            }else {
                IdentityAuthResDTO resDTO = identityAuthService.authReq(reqDTO);
                result = new IdentityAuthResult<>(resDTO);
                log.info("身份认证，响应:{}", result);
            }
        }catch (BusinessException be){
            result = new IdentityAuthResult<>(be.getCode(),be.getResMessage());
        }catch (Exception e){
            result = new IdentityAuthResult<>(ErrorCodeEnum.SYSTEM_ERROR.getCode(),ErrorCodeEnum.SYSTEM_ERROR.getMsg());
        }
        try {
            String res = objectMapper.writeValueAsString(result);
            if(securityKey!=null) {
                String pwd = HMacSHAUtil.HMacSHA256(res, securityKey);
                HeaderAuthDTO authDTO = new HeaderAuthDTO();
                authDTO.setAuthCode(pwd);
                HeaderUtil.setResponse(response, authDTO);
            }
            return res;
        } catch (JsonProcessingException e) {
            return "SYSTEM_ERROR";
        }

    }
}
