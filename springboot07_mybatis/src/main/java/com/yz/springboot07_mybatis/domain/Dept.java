package com.yz.springboot07_mybatis.domain;

import lombok.Data;

import java.util.List;

@Data
public class Dept {

    private Integer deptId;

    private String deptName;

    private List<Employee> employeeList;
}
