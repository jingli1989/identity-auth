package com.identity.auth.web.interceptor;

import com.identity.auth.IdentityAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证处理
 * Created by lijing on 2017/10/28 0028.
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private IdentityAuthService identityAuthService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String context = httpServletRequest.getHeader("AUTH-CONTEXT");
        String memberId = httpServletRequest.getHeader("AUTH-MEMBER");
//        identityAuthService.authReq(memberId);
        log.info("请求:{},密文摘要:{}",memberId,context);
        log.info("请求Obj:{}",o);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
