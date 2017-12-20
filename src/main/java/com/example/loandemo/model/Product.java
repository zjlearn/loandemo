package com.example.loandemo.model;

import java.math.BigDecimal;

/**
 * create by zhangjun1 on 2017/12/20
 * 代表具体的产品
 */
public class Product {
    private Long id;
    private String name;
    private String productNo; //产品编号
    private int type; // 产品类别
    private BigDecimal amount;  //总额
    private BigDecimal restAmount; // 剩余
    private Integer period;  //期限
    private double Profit;
    private Integer status;
    private Long createTime;
    private Long updateTime;


}
