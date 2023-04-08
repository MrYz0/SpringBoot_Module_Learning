package com.yz.springboot_mybatis_plus.mapper;

import com.yz.springboot_mybatis_plus.domain.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yangzhou
* @description 针对表【t_employee】的数据库操作Mapper
* @createDate 2023-04-08 15:46:19
* @Entity com.yz.springboot_mybatis_plus.domain.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}




