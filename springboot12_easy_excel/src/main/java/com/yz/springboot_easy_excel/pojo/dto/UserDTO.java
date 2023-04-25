package com.yz.springboot_easy_excel.pojo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


@Data
public class UserDTO {

    private Integer id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("性别")
    private String sex;


}
