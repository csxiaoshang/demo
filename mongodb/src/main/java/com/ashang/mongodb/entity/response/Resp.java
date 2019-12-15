package com.ashang.mongodb.entity.response;


@lombok.Data
public class Resp<T> {

    /**
     * error_code : 2000
     * error_message : 错误信息
     * data : {}
     */

    private Integer error_code;
    private String error_message;
    private T data;
}
