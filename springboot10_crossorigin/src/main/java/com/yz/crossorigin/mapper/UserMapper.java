package com.yz.crossorigin.mapper;

import com.yz.crossorigin.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yangzhou
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2023-04-22 17:14:38
* @Entity com.yz.crossorigin.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




