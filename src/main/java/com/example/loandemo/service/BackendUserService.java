package com.example.loandemo.service;

import com.example.loandemo.model.auth.BackendUser;

/**
 * create by zhangjun1 on 2017/12/25
 * 负责后台用户管理的服务信息
 */
public interface BackendUserService {
    public BackendUser findByUid(String uid);
}
