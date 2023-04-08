package com.yz.springboot_exception.mapper;

import com.yz.springboot_exception.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yangzhou
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2023-04-08 13:20:50
* @Entity com.yz.springboot_exception.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




