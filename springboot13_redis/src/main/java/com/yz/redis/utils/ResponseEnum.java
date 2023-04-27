package com.yz.redis.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseEnum {


    SUCCESS(200,"成功"),
    FAIL(201,"失败"),
    GET_ERROR(202,"查询失败");

    private Integer code;

    private String message;
}
