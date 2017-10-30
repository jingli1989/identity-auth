package com.identity.auth.test.api;

import com.identity.auth.common.util.IdentityAuthResult;
import com.identity.auth.model.req.IdentityAuthReqDTO;
import com.identity.auth.model.res.IdentityAuthResDTO;
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
    Call<IdentityAuthResult<IdentityAuthResDTO>> auth(@Header("AUTH-MEMBER")String memberId,@Header("AUTH-CONTEXT") String context,@Body IdentityAuthReqDTO reqDto);

}

