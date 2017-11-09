package com.identity.auth.test.http;

import com.identity.auth.common.util.VerifyParamUtil;
import com.identity.auth.service.model.req.IdentityAuthReqDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lijing on 2017/10/30 0030.
 */
public class Test {
    public static void main(String[] args) {

        System.out.println("============");
        IdentityAuthReqDTO reqDTO = new IdentityAuthReqDTO();
        Set<String> set = new HashSet<>();
        set.add("idCard");
        set.add("idName");
        set.add("memberId");
        set.add("memberTransId");
//        set.add("memberTransDate");
        VerifyParamUtil.validateObjectFilter(reqDTO,set);
    }
}
