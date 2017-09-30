package com.example.loandemo.model.view;

/**
 * @(#)PageBean.java, 2017/9/15.
 * <p/>
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */


import com.example.loandemo.model.view.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghao1 on 2017/9/15.
 */
public class PageBean<T> {

    /**
     * 分页信息
     */
    private Pagination pagination;

    /**
     * 结果列表
     */
    private List<T> result;

    public PageBean(List<T> result, int page, int size, int total) {
        this.pagination = new Pagination(page, size, total);
        if (result == null) {
            this.result = new ArrayList<T>(0);
        } else {
            this.result = result;
        }
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pagination=" + pagination +
                ", result=" + result +
                '}';
    }
}