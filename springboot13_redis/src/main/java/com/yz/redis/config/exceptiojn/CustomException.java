package com.yz.redis.config.exceptiojn;

import com.yz.redis.utils.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomException extends RuntimeException{

    private Integer code;

    private String message;

    public CustomException(ResponseEnum responseEnum){
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

}
