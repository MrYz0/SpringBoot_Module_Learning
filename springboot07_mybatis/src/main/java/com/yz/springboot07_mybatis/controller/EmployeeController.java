package com.yz.springboot07_mybatis.controller;

import com.yz.springboot07_mybatis.domain.Employee;
import com.yz.springboot07_mybatis.service.EmployeeService;
import com.yz.springboot07_mybatis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    public Result save(@RequestBody Employee employee){
        employee.setDate(new Date());
        employeeService.save(employee);
        return Result.ok();
    }

    @PutMapping
    public Result update(@RequestBody Employee employee){
        employeeService.update(employee);
        return Result.ok();
    }

    @GetMapping("list")
    public Result list(){
        List<Employee> list = employeeService.list();
        return Result.ok(list);
    }
}
