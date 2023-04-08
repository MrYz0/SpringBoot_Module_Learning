package com.yz.springboot07_mybatis.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseEnum {

    SUCCESS(200,"成功"),
    FAIl(201,"失败");

    private Integer code;

    private String message;
}
