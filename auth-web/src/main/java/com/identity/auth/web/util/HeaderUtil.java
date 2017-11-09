package com.identity.auth.web.util;

import com.identity.auth.service.model.HeaderAuthDTO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * header处理工具
 * Created by lijing on 2017/11/4 0004.
 */
@Slf4j
public class HeaderUtil {

    /**
     * 获取请求头信息
     * @param request http请求
     * @return 请求头中信息
     */
    public static HeaderAuthDTO getHeaderReq(HttpServletRequest request){
        String context = request.getHeader("AUTH-CONTEXT");
        HeaderAuthDTO authDTO = new HeaderAuthDTO();
        authDTO.setAuthCode(context);
        return authDTO;
    }

    /**
     * 设置响应头
     * @param response http响应
     * @param authDTO 响应信息
     */
    public static void setResponse(HttpServletResponse response,HeaderAuthDTO authDTO){
        response.setHeader("AUTH-CONTEXT",authDTO.getAuthCode());
    }
}
