package com.example.loandemo.model;

/**
 * create by zhangjun1 on 2017/12/20
 */
public enum ProductType {

    SHORT(1, "短期");

    private int value;
    private  String desc;

    ProductType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
