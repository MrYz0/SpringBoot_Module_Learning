package com.yz.springboot_easy_excel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yz.springboot_easy_excel.pojo.User;
import com.yz.springboot_easy_excel.pojo.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author yangzhou
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-04-25 15:36:10
* @Entity springboot_easy_excel.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void insertBatch(List<UserDTO> list);
}




