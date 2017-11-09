package com.identity.auth.test.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.identity.auth.common.util.HMacSHAUtil;
import com.identity.auth.service.model.req.IdentityAuthReqDTO;
import com.identity.auth.test.api.IdentityAuthApi;
import com.identity.auth.test.util.ResUtil;
import com.identity.auth.test.util.RetrofitUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by lijing on 2017/10/30 0030.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-test.xml")
public class TestHttp {

    private IdentityAuthApi api;

    @Autowired
    public void initApi(){
        String webPath = "http://localhost:33102";
        Retrofit retrofit = RetrofitUtil.createRetrofit(null, webPath);
        api = retrofit.create(IdentityAuthApi.class);
    }

//    public static void main(String[] args) {
////        TestHttp testHttp = new TestHttp();
////        testHttp.initApi();
////        testHttp.query();
//    }
    /**
     * 查询
     */
    @Test
    public void query() throws JsonProcessingException {
        IdentityAuthReqDTO reqDTO = new IdentityAuthReqDTO();
//        log.info("查询配置测试-请求参数:reqDTO:{}",reqDTO);
        ObjectMapper objectMapper = new ObjectMapper();
        String reqStr = objectMapper.writeValueAsString(reqDTO);
        String pwd = HMacSHAUtil.HMacSHA256(reqStr,"123");
        Call<String> call = api.auth("123",pwd,reqStr);
        ResUtil.printRes(call);
    }
}
