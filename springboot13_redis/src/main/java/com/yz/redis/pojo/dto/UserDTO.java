package com.yz.redis.pojo.dto;

import lombok.Data;

@Data
public class UserDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 电话
     */
    private String phone;
    /**
     * 昵称
     */
    private String nickName;
}
