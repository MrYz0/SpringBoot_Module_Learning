package com.yz.springboot07_mybatis.service.impl;

import com.yz.springboot07_mybatis.domain.Employee;
import com.yz.springboot07_mybatis.mapper.EmployeeMapper;
import com.yz.springboot07_mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> list() {
        return employeeMapper.list();
    }

    @Override
    public int save(Employee employee) {
        return employeeMapper.save(employee);
    }

    @Override
    public int update(Employee employee) {
        return employeeMapper.update(employee);
    }
}
