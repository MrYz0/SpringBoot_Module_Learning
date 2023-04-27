package com.yz.redis.service;

import com.yz.redis.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yz.redis.pojo.dto.LoginFormDTO;
import com.yz.redis.utils.Result;

/**
* @author yangzhou
* @description 针对表【user】的数据库操作Service
* @createDate 2023-04-27 09:47:10
*/
public interface UserService extends IService<User> {

    Result sendCode(String phone);


    Result login(LoginFormDTO loginFormDTO);
}
