package com.yz.redis.utils;

/**
 * redis常量
 *
 * @author yangzhou
 * @date 2023/04/27
 */
public class RedisConstants {

    /**
     * redis中验证码的key
     */
    public static final String LOGIN_CODE_KEY = "login:code:";
    /**
     * redis中验证码有效期
     */
    public static final Long LOGIN_CODE_TTL = 2L;
    /**
     * redis中用户信息的key
     */
    public static final String LOGIN_USER_KEY = "login:token:";
    /**
     * redis中用户信息的有效期
     */
    public static final Long LOGIN_USER_TTL = 36000L;
}
