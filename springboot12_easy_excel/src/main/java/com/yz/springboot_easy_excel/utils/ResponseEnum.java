package com.yz.springboot_easy_excel.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(200,"成功"),
    FAIL(201,"失败");
    private Integer code;

    public String message;
}
