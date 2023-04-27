package com.yz.redis.utils;

import lombok.Data;

@Data
public class Result <T>{



    private Integer code;

    private String  message;

    private T data;

    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data == null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(T data,Integer code, String message) {
        Result<T> result = build(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> build(T data,ResponseEnum responseEnum){
        Result<T> result = build(data);
        result.setCode(responseEnum.getCode());
        result.setMessage(responseEnum.getMessage());
        return result;
    }


    public static <T> Result<T> ok(){
        return Result.ok(null);
    }

    public static <T> Result<T> ok(T data){
        Result<T> result = build(data);
        return Result.build(data,ResponseEnum.SUCCESS);
    }

    public static <T> Result<T> fail(){
        return Result.fail(null);
    }

    public static <T> Result<T> fail(T data){
        Result<T> result = build(data);
        return Result.build(data,ResponseEnum.FAIL);
    }

    public Result<T> code(Integer code){
        this.code = code;
        return this;
    }

    public Result<T> message(String message){
        this.message = message;
        return this;
    }

}
