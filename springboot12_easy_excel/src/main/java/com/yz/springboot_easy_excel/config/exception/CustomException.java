package com.yz.springboot_easy_excel.config.exception;

import com.yz.springboot_easy_excel.utils.ResponseEnum;
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
    private String message;
    /**
     *
     * @param message 错误消息
     */
    public CustomException(String message) {
        this.message = message;
    }

    /**
     *
     * @param message 错误消息
     * @param code 错误码
     */
    public CustomException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    /**
     *
     * @param resultCodeEnum 接收枚举类型
     */
    public CustomException(ResponseEnum resultCodeEnum) {
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }

    /**
     *
     * @param resultCodeEnum 接收枚举类型
     * @param cause 原始异常对象
     */
    public CustomException(ResponseEnum resultCodeEnum, Throwable cause) {
        super(cause);
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }

}
