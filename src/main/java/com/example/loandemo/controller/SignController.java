package com.example.loandemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.loandemo.util.IPSRSACryptoUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by zhangjun1 on 2018/1/9
 */
@RestController
public class SignController {

    @RequestMapping("/sign")
    public JSONObject sign(@RequestParam("merchantID") String merchantID,
                           @RequestParam("operationType") String operationType,
                           @RequestParam("request") String request){
        return IPSRSACryptoUtil.reqDepData(merchantID, operationType, request);
    }

}
