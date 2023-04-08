package com.yz.springboot07_mybatis.mapper;

import com.yz.springboot07_mybatis.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {


    List<Employee> list();

    int save(Employee employee);

    int update(Employee employee);
}
