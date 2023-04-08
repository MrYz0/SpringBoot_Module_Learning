package com.yz.springboot_mybatis_plus.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(200,"成功"),
    FAIL(201,"失败");

    private Integer code;

    private String message;
}
