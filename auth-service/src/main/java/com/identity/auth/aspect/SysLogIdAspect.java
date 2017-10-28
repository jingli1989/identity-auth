package com.identity.auth.aspect;

import com.identity.auth.common.annotation.SysLogId;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切面处理
 */
@Aspect
@Component
@Slf4j
public class SysLogIdAspect {

    @Pointcut("@annotation(com.identity.auth.common.annotation.SysLogId)")
    public void logIdPointCut() {

    }

    /**
     * 环绕通知[SysLogId]
     * api接口标准，最后一个必须是日志id
     * 参数校验只校验第一个对象，api尽快定义成单对象请求
     *
     * @param point 切面
     * @return 方法返回的结果对象
     * @throws Throwable 出现差异
     */
    @Around("logIdPointCut()")
    public Object aroundReturning(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        SysLogId syslog = method.getAnnotation(SysLogId.class);
        log.info("功能:{} 执行方法:{}.{} 开始执行 ",syslog.value(),className,methodName);
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        log.info("功能:{}, 执行方法:{}.{} 执行完成耗时:{}ms",syslog.value(),className,methodName,time);
        return result;
    }
}
