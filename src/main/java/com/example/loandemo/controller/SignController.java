package com.example.loandemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.loandemo.model.dto.UserRegisterDTO;
import com.example.loandemo.util.IPSCONSTANTS;
import com.example.loandemo.util.IPSRSACryptoUtil;
import com.example.loandemo.util.http.HttpManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * create by zhangjun1 on 2018/1/9
 */
@RestController
@RequestMapping("/xhr")
public class SignController {

    @Autowired
    HttpManager httpManager;

    @RequestMapping("/sign")
    public JSONObject sign(@RequestParam("merchantID") String merchantID,
                           @RequestParam("operationType") String operationType,
                           @RequestParam("request") String request){
        return IPSRSACryptoUtil.genReqData(merchantID, operationType, request);
    }

    @RequestMapping("/getSign")
    public JSONObject sign(){
        String merchantID = "1186570022";
        String operationType = "user.register";
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setMerBillNo("23235413");
        userRegisterDTO.setMerDate("2018-01-10");
        userRegisterDTO.setUserName("zj");
        userRegisterDTO.setUserType("1");
        userRegisterDTO.setUserRole("1");
        userRegisterDTO.setUserName("章的");
        userRegisterDTO.setRealName("哈哈");
        userRegisterDTO.setMobileNo("13016413998");
        userRegisterDTO.setBizType("1");
        userRegisterDTO.setEnterName("");
        userRegisterDTO.setIsAssureCom("1");
        userRegisterDTO.setWebUrl("http://127.0.0.1:8080/p2p-dep/test/p2pweb.html");
        userRegisterDTO.setS2SUrl("http://127.0.0.1:8080/p2p-dep/test/p2pweb.html");
        userRegisterDTO.setIdentNo("411527199210145036");
        String reqStr= JSONObject.toJSONString(userRegisterDTO);
        JSONObject result =  IPSRSACryptoUtil.genReqData(merchantID, operationType, reqStr);
        System.out.println(result);


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
        ResponseEntity<String> response = httpManager.restTemplate().postForEntity(url, request, String.class);
        System.out.println(response.getBody());
        return result;

    }
}
