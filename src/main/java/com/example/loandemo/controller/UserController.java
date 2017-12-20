package com.example.loandemo.controller;

import com.example.loandemo.model.view.Result;
import com.example.loandemo.model.view.ResultGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zjlearn on 2017/9/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }


    @RequestMapping("register")
    public Result register(){
        return ResultGenerator.genSuccessResult();
    }
}
