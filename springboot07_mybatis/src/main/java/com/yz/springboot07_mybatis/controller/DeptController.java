package com.yz.springboot07_mybatis.controller;

import com.yz.springboot07_mybatis.domain.Dept;
import com.yz.springboot07_mybatis.service.DeptService;
import com.yz.springboot07_mybatis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;


    @GetMapping("list")
    public Result list(){
        List<Dept> list = deptService.list();
        return Result.ok(list);
    }
}
