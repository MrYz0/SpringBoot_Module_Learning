package com.yz.springboot07_mybatis.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {

    private Integer employeeId;

    private String employeeName;

    private Date date;

    private Integer deptId;

    private Dept dept;
}
