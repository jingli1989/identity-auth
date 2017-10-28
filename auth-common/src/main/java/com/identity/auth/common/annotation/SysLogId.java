package com.identity.auth.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志id
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogId {
    String value() default "";
}