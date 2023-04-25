package com.yz.springboot_logback.mapper;

import com.yz.springboot_logback.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yangzhou
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-04-25 12:57:07
* @Entity com.yz.springboot_logback.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




