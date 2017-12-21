package com.example.loandemo.model;

/**
 * create by zhangjun1 on 2017/9/30
 */
public class User {
    private Long id;
    private String nickname; //昵称
    private String username; //账号
    private String name;

    private String password; //密码;
    private String salt;//加密密码的盐
    private String sex;
    private String idCard;

    private int state;  //用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

}
