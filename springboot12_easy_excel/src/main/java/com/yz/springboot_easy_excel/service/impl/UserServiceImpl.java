package com.yz.springboot_easy_excel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yz.springboot_easy_excel.domain.User;
import com.yz.springboot_easy_excel.service.UserService;
import com.yz.springboot_easy_excel.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author yangzhou
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-04-23 20:46:41
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




