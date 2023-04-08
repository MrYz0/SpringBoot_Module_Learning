package com.yz.springboot_restful.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
public enum ResponseEnum {

    SUCCESS(200,"成功"),
    Fail(201,"失败");

    private Integer code;

    private String message;

    private ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
