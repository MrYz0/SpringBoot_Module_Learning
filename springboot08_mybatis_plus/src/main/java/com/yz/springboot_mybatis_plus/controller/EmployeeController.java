package com.yz.springboot_mybatis_plus.controller;

import com.yz.springboot_mybatis_plus.domain.Employee;
import com.yz.springboot_mybatis_plus.service.EmployeeService;
import com.yz.springboot_mybatis_plus.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("list")
    public Result list(){
        List<Employee> list = employeeService.list();
        return Result.ok(list);
    }
}
