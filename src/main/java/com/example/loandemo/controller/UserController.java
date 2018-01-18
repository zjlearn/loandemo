package com.example.loandemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.loandemo.model.view.Result;
import com.example.loandemo.model.view.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by zjlearn on 2017/9/30.
 * 处理登录注册的逻辑
 */
@RestController
@RequestMapping("/xhr/user")
public class UserController {

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("进入hello");
        return "hello， 5465";
    }


    @RequestMapping("/register")
    public Result register(@RequestParam("mobileNo") String mobileNo, @RequestParam("userName") String userName,
                           @RequestParam("password") String password, @RequestParam("inviteCode") String inviteCode) {
        return ResultGenerator.genSuccessResult();
    }


    // form 表单方式提交
    @RequestMapping("/login")
    public Result login(@RequestParam("uid") String uid, @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(uid, password);
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
        }
        //6、退出
        subject.logout();
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/salt")
    public Result salt(){
        long  salt=0L;
        try {
            SecureRandom  secureRandom = SecureRandom.getInstance("md5");
            salt = secureRandom.nextLong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult(salt);
    }
}
