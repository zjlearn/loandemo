package com.example.loandemo.model.view;

/**
 * @(#)Pagination.java, 2017/9/15.
 * <p/>
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * Created by wanghao1 on 2017/9/15.
 */
public class Pagination {

    /**
     * 当前页
     */
    private int page;

    /**
     * 页大小
     */
    private int size;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 结果总数
     */
    private int total;

    public Pagination() {
    }

    /**
     * 构造方法
     */
    public Pagination(int page, int size, int total) {
        this.page = page;
        this.size = size;
        this.total = total;
        if (size > 0) {
            this.totalPage = 1 + (total - 1) / size;
        } else {
            this.totalPage = 1;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "page=" + page +
                ", size=" + size +
                ", totalPage=" + totalPage +
                ", total=" + total +
                '}';
    }
}