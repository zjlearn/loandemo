package com.example.loandemo.util;


/**
 * create by zhangjun1 on 2017/12/3
 */
public class IpsResponse {
    public String resultCode;
    public  String resultMsg;
    public String sign;
    public String response;
    public String message;  //当返回的是html页面时， 保存html页面
    public  Boolean isJson;  //判断是否是json格式的字符串

    public IpsResponse(String resultCode, String resultMsg, String sign, String response){
        this.resultCode=resultCode;
        this.resultMsg=resultMsg;
        this.sign=sign;
        this.response= IpsCrypto.triDesDecrypt(response,ModelParam.encryptKey, ModelParam.iv);
        this.isJson=true;
    }
    //this constructor handle exception contidional
    public IpsResponse(String message){
        this.message=message;
        this.isJson=false;
    }
    @Override
    public String toString() {
        if(isJson)
            return "IpsResponse is: "+"\n"+
                    "resultCode: "+resultCode+"\n"+
                    "resultMsg: "+resultMsg +"\n"+
                    "response: "+response;
        else
            return  message;
    }
}