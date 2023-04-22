package com.yz.crossorigin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yz.crossorigin.domain.User;
import com.yz.crossorigin.service.UserService;
import com.yz.crossorigin.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author yangzhou
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-04-22 17:14:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




