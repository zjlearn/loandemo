package com.example.loandemo.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Base64;

/**
 * create by zhangjun1 on 2018/1/9
 */
@RunWith(JUnit4.class)
public class IPSRSACryptoUtilTest {
    @Test
    public void testGenReq(){
        String merchantID = "123123";
        String operationType = "21341";
        String req = "3241234";
        try {
            byte []  t = RSA.encrypt(CertUtil.getEncryptCertPublicKey(), req);
            String reqDate = Base64.getEncoder().encodeToString(t);
            RSA.decrypt(CertUtil.getSignCertPrivateKey() , Base64.getDecoder().decode(reqDate));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
