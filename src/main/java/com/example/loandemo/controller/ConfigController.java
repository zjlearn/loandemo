package com.example.loandemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.loandemo.util.ModelParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by zhangjun1 on 2017/12/20
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @RequestMapping("/encryptKey")
    public String modelParam(){
        return JSON.toJSONString(ModelParam.encryptKey);
    }
}
