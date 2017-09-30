package com.example.loandemo.model.view;

/**
 * create by zhangjun1 on 2017/9/30
 */

/**
 * 响应结果生成工具类
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";

    public static Result genSuccessResult() {
        return new Result()
                .setCode(HttpStatus.OK.value())
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result genSuccessResult(T data) {
        return new Result()
                .setSuccess(true)
                .setCode(HttpStatus.OK.value())
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static <T> Result genSuccessResult(int code, T data) {
        return new Result()
                .setSuccess(true)
                .setCode(code)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult() {
        return new Result()
                .setSuccess(false)
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMessage(DEFAULT_FAIL_MESSAGE);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setSuccess(false)
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMessage(message);
    }

    public static Result genFailResult(int code, String message) {
        return new Result()
                .setSuccess(false)
                .setCode(code)
                .setMessage(message);
    }

}
