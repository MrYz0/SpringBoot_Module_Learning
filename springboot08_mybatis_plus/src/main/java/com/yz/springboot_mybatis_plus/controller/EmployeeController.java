package com.yz.springboot_mybatis_plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yz.springboot_mybatis_plus.domain.Employee;
import com.yz.springboot_mybatis_plus.domain.vo.EmployeeVo;
import com.yz.springboot_mybatis_plus.service.EmployeeService;
import com.yz.springboot_mybatis_plus.utils.Result;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    /**
     * 分页条件查询
     *
     * @param page       页面
     * @param limit      限制
     * @return {@link Result}
     */
    @GetMapping("{page}/{limit}")
    public Result page(@PathVariable Long page,
                       @PathVariable Long limit,
                       @RequestBody(required = false) EmployeeVo employeeVo){
        // 创建page对象
        Page<Employee> pageParam = new Page<>(page, limit);
        // 调用方法
        // 判断查询对象是否为空
        // 1.为空查询所有
        if(employeeVo == null){
            Page<Employee> employeePage = employeeService.page(pageParam, null);
            return Result.ok(employeePage);
        }
        // 2.不为空条件查询
        String employeeName = employeeVo.getEmployeeName();
        QueryWrapper<Employee> wrapper = new QueryWrapper<Employee>();
        if (!StringUtils.isEmpty(employeeName)){
            wrapper.like("employee_name",employeeName);
        }

        employeeService.page(pageParam,wrapper);

        // 返回对象
        return Result.ok(pageParam);
    }

    @GetMapping("list")
    public Result list(){
        List<Employee> list = employeeService.list();
        return Result.ok(list);
    }

    // 增
    @PostMapping
    public Result save(@RequestBody Employee employee){
        boolean result = employeeService.save(employee);
        if (result){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    // 删
    @DeleteMapping("{employeeId}")
    public Result delete(@PathVariable Integer employeeId){
        boolean result = employeeService.removeById(employeeId);
        if (result){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    // 改
    @PutMapping
    public Result edit(@RequestBody Employee employee){
        boolean result = employeeService.updateById(employee);
        if (result){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    // 查
    @GetMapping("{employeeId}")
    public Result getEmployeeByEmployeeId(@PathVariable Integer employeeId){
        Employee employee = employeeService.getById(employeeId);
        return Result.ok(employee);
    }
}
