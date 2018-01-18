package com.example.loandemo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * create by zhangjun1 on 2017/12/3
 */
@Component
public class ModelParam {

    public static  String TestServerAdderss = "";
    public static  String encryptKey ="";
    public static  String iv ="";
    public static  String mdstr ="";
    public static  String merchantID ="";

    @Value("${ipsServerAdderss}")
    public  void setTestServerAdderss(String testServerAdderss) {
        TestServerAdderss = testServerAdderss;
    }
    @Value("${encryptKey}")
    public  void setEncryptKey(String encryptKey) {
        ModelParam.encryptKey = encryptKey;
    }
    @Value("${iv}")
    public  void setIv(String iv) {
        ModelParam.iv = iv;
    }
    @Value("${mdstr}")
    public  void setMdstr(String mdstr) {
        ModelParam.mdstr = mdstr;
    }
    @Value("${merchantID}")
    public  void setMerchantID(String merchantID) {
        ModelParam.merchantID = merchantID;
    }
}
