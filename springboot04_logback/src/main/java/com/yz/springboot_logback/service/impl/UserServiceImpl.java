package com.yz.springboot_logback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yz.springboot_logback.pojo.User;
import com.yz.springboot_logback.service.UserService;
import com.yz.springboot_logback.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author yangzhou
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-04-25 12:57:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




