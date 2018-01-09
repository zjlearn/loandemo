package com.example.loandemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.loandemo.model.view.Result;
import com.example.loandemo.model.view.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zjlearn on 2017/9/30.
 */
@RestController
@RequestMapping("/xhr/user")
public class UserController {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("进入hello");
        return "hello， 5465";
    }


    @RequestMapping("/register")
    public Result register(){
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/login")
    public Result login(@RequestBody JSONObject user){
        System.out.println("login: "+ user);
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(uid, password);
//        try {
//            //4、登录，即身份验证
//            subject.login(token);
//        } catch (AuthenticationException e) {
//            //5、身份验证失败
//        }
//
//        //6、退出
//        subject.logout();
      return ResultGenerator.genSuccessResult();
    }
}
