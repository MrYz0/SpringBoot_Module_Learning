package com.yz.springboot07_mybatis.service;

import com.yz.springboot07_mybatis.domain.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> list();

    int save(Employee employee);

    int update(Employee employee);
}
