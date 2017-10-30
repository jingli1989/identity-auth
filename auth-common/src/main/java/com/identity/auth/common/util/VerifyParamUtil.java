package com.identity.auth.common.util;

import com.identity.auth.common.enums.ErrorCodeEnum;
import com.identity.auth.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 参数校验类
 * user:zyd.
 * date:2017/8/30.
 * time:17:20.
 * version:1.0.0.
 */
@Slf4j
public class VerifyParamUtil {
    private final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();


    /**
     * 请求参数非空、格式验证，请求对象
     *
     * @param object 请求校验参数
     */
    public static void validateObject(Object object) {
        long start = System.currentTimeMillis();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (violations.size() == 0) return;
        for (ConstraintViolation<Object> violation : violations) {

            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR.getCode(), violation.getMessage());
        }
        log.info("基本数据验证耗时:{}", System.currentTimeMillis() - start);
    }

    /**
     * 请求参数非空、格式验证，请求对象
     *
     * @param object 请求校验参数
     */
    public static void validateObjectFilter(Object object,Set<String> filterSet) {
        long start = System.currentTimeMillis();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (violations.size() == 0) return;
        for (ConstraintViolation<Object> violation : violations) {
            if(!filterSet.contains(violation.getPropertyPath().toString()))
                throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR.getCode(), violation.getMessage());
        }
        log.info("基本数据验证耗时:{}", System.currentTimeMillis() - start);
    }

    /**
     * 多个对象 请求参数非空、格式验证，请求对象
     *
     * @param objects 请求校验参数
     */
    public static void validateObjects(Object... objects) {
        for (Object obj : objects) {
            validateObject(obj);
        }
    }

    /**
     * 请求参数校验 指定属性
     *
     * @param object 请求校验参数
     */
    public static void validateProperty(Object object, String... propertys) {
        Validator validator = factory.getValidator();
        for (String property : propertys) {
            Set<ConstraintViolation<Object>> violations = validator.validateProperty(object, property);
            if (violations.size() == 0) continue;

            for (ConstraintViolation<Object> violation : violations) {
                throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR.getCode(), violation.getMessage());
            }

        }
    }


}
