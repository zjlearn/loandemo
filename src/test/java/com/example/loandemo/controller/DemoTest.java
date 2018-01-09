package com.example.loandemo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * create by zhangjun1 on 2017/12/25
 */
@RunWith(JUnit4.class)
public class DemoTest {
    @Test
    public void testChangeStr(){
        double d =1.3;
        int compensationCount = (int) Math.round(10- d);
        System.out.println(compensationCount);
    }
    public  void set(String str){
        str= "asf";
    }
}
