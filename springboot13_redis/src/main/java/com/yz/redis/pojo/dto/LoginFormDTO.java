package com.yz.redis.pojo.dto;

import lombok.Data;

@Data
public class LoginFormDTO {

    /**
     * 电话
     */
    private String phone;
    /**
     * 验证码
     */
    private String code;
}
