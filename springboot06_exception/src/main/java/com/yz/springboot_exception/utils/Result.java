package com.yz.springboot_exception.utils;

import com.sun.org.apache.regexp.internal.RE;
import lombok.Data;
/**
 * 全局统一返回结果类
 *
 */
@Data
public class Result <T>{

    // 返回状态码
    private Integer code;
    // 返回消息
    private String message;
    // 返回数据
    private T data;

    public Result(){}

    // 返回数据
    private static <T> Result<T> build(T data){
        Result<T> result = new Result<>();
        if (data != null){
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(T body,Integer code,String message){
        Result<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    public static <T> Result<T> build(T body,ResponseEnum responseEnum){
        Result<T> result = build(body);
        result.setCode(responseEnum.getCode());
        result.setMessage(responseEnum.getMessage());
        return result;
    }

    public static <T> Result<T> ok(){
        return Result.ok(null);
    }

    public static <T> Result<T> ok(T data){
        Result<T> result = build(data);
        return build(data,ResponseEnum.SUCCESS);
    }

    public static <T> Result<T> fail(){
        return Result.fail(null);
    }

    public static <T> Result<T> fail(T data){
        Result<T> result = build(data);
        return build(data,ResponseEnum.SUCCESS);
    }


    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }
}
