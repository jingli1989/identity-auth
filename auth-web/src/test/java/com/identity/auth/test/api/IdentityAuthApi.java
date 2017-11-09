package com.identity.auth.test.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


/**
 * 配置管理
 * Created by lijing on 2017/10/24 0024.
 */

public interface IdentityAuthApi {
    @POST("/identity-auth/identity/auth")
    Call<String> auth(@Header("AUTH-MEMBER")String memberId,@Header("AUTH-CONTEXT") String context,@Body String reqDto);

}

