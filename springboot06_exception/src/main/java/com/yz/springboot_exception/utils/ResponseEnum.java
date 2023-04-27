package com.yz.springboot_exception.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(200,"成功"),
    Fail(201,"失败"),
    SERVICE_ERROR(20001,"服务异常");

    /**
     * 其它业务状态码
     * SERVICE_ERROR(2012, "服务异常"),
     * DATA_ERROR(204, "数据异常"),
     * ILLEGAL_REQUEST(205, "非法请求"),
     * REPEAT_SUBMIT(206, "重复提交"),
     * ARGUMENT_VALID_ERROR(210, "参数校验异常"),
     */

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;


}
