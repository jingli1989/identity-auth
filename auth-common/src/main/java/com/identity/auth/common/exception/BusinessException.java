package com.identity.auth.common.exception;

import lombok.Getter;

/**
 * 自定义业务处理异常
 * user:zyd.
 * date:2017/8/28.
 * time:14:45.
 * version:1.0.0.
 */
public class BusinessException extends RuntimeException {
    @Getter
    private String code;

    @Getter
    private String resMessage;


    public BusinessException(String code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.resMessage = message;
    }

    public BusinessException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.resMessage = message;
    }
}
