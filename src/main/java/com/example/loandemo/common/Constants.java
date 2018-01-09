package com.example.loandemo.common;

import com.example.loandemo.util.DateUtil;

import java.util.Date;
import java.util.UUID;

/**
 * create by zhangjun1 on 2017/12/25
 */
public class Constants {
    public static final String LOG_FORMAT = "time=" + DateUtil.getToLongString(new Date()) + ", module=%s, value=%s, topic=%s, id= %s, "+  "message={\"%s\"}";
    public static final String CHARSET = "UTF-8";
    public static final String KEY_ALGORITHM_SHA512 = "SHA-512";
    public static final String KEY_ALGORITHM_MD5 = "MD5";
    public static final String KEY_ALGORITHM_SHA1 = "SHA-1";
    public static final String KEY_ALGORITHM_3DES = "DESede";
    public static final String KEY_3DES_TRANS = "DESede/ECB/PKCS5Padding";
    public static final String KEY_3DES_KEY = "XYCM3DES0987654321@2016";

    public Constants() {
    }
}
