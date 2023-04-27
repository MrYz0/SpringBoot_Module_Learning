package com.yz.redis.mapper;

import com.yz.redis.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yangzhou
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-04-27 09:47:10
* @Entity com.yz.redis.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




