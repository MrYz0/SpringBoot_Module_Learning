package com.yz.springboot_mybatis_plus.mapper;

import com.yz.springboot_mybatis_plus.domain.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yangzhou
* @description 针对表【t_dept】的数据库操作Mapper
* @createDate 2023-04-08 15:46:24
* @Entity com.yz.springboot_mybatis_plus.domain.Dept
*/
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

}




