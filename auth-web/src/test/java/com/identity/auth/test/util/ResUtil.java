package com.identity.auth.test.util;

import com.identity.auth.common.util.IdentityAuthResult;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by lijing on 2017/10/24 0024.
 */
@Slf4j
public class ResUtil {
    /**
     * 结果打印
     * @param call call
     * @param <T> 泛型
     */
    public static  <T> void printRes(Call<IdentityAuthResult<T>> call){
        try {
            long startTime = System.currentTimeMillis();
            Response<IdentityAuthResult<T>> response = call.execute();
            if (response.code() == 200) {
                System.out.println("执行结果:" + response.body());
                IdentityAuthResult<T> result = response.body();
                log.info("执行成功,耗时:{}ms,响应结果:{}",System.currentTimeMillis()-startTime,result);
                return;
            }
            log.error("执行失败，http响应code:{}",response.code());
        } catch (IOException e) {
            log.error("执行失败，原因;",e);
        }
    }
}
