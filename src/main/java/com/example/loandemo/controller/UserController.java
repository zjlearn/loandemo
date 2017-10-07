package com.example.loandemo.controller;

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
}
