package com.yz.springboot_exception.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomException extends RuntimeException{

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;
}
