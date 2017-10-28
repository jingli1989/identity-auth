package com.identity.auth.common.util;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 统一返回结果封装对象
 *
 * @param <T> 泛型对象
 */
@Getter
@Setter
@ToString
public class IdentityAuthResult<T> implements Serializable {
    private static final long serialVersionUID = 8350327877975282483L;

    private boolean success;  //表示调用是否成功 ,如果为true,则可以调用getResult,如果为false,则调用errorCode来获取出错信息

    private T data;  //获取调用返回值

    private String errorCode; //获取错误码

    private String errorMsg;

    public IdentityAuthResult() {
    }

    public IdentityAuthResult(T data) {
        this.success = true;
        this.data = data;
    }

    public IdentityAuthResult(String errorCode, String errorMsg) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public void setResult(T result) {
        success = true;
        this.data = result;
    }

    public void setErrorResult(String errorCode, String errorMsg) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    @Override
    public int hashCode() {
        int result1 = (success ? 1 : 0);
        result1 = 31 * result1 + data.hashCode();
        result1 = 31 * result1 + errorCode.hashCode();
        return result1;
    }

}
