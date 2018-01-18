package com.example.loandemo.util.IPS;

import com.alibaba.fastjson.JSONObject;
import com.example.loandemo.util.IPSCONSTANTS;
import com.example.loandemo.util.IPSRSACryptoUtil;
import com.example.loandemo.model.IpsResponse;
import com.example.loandemo.util.http.HttpManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

/**
 * create by zhangjun1 on 2018/1/14
 */
@Component
public class IPSHttpUtil {

    @Autowired
    HttpManager httpManager;

    // 该过程包括加密，签名， 和发送请求
    public  IpsResponse post(String merchantID, String operationType, String reqStr){
        JSONObject result =  IPSRSACryptoUtil.genReqData(merchantID, operationType, reqStr);
        // begin to send request
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/x-www-form-urlencoded");

        headers.setAll(map);

        //使用RestTemplate向MPS发送消息时, 必须使用下面的写法。
        //MPS强制的提交方式是： POST， 表单， 参数提交方式
        MultiValueMap<String, String> req_form  = new LinkedMultiValueMap<>();
        req_form.add(IPSCONSTANTS.SIGN_PARAM_merchantID, result.getString(IPSCONSTANTS.SIGN_PARAM_merchantID));
        req_form.add(IPSCONSTANTS.SIGN_PARAM_operationType, result.getString(IPSCONSTANTS.SIGN_PARAM_operationType));
        req_form.add(IPSCONSTANTS.SIGN_PARAM_REQUEST, result.getString(IPSCONSTANTS.SIGN_PARAM_REQUEST));
        req_form.add(IPSCONSTANTS.SIGN_PARAM_SIGN, result.getString(IPSCONSTANTS.SIGN_PARAM_SIGN));

        HttpEntity<?> request = new HttpEntity<>(req_form, headers);

        String url= "http://113.207.54.122:8011/p2p-dep/gateway.htm";

        //向消息中心件发送消息
        IpsResponse response = httpManager.restTemplate().postForObject(url, request, IpsResponse.class);
        return response;
    }
}
