package com.yz.springboot_exception.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yz.springboot_exception.domain.User;
import com.yz.springboot_exception.service.UserService;
import com.yz.springboot_exception.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author yangzhou
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-04-08 13:20:50
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




