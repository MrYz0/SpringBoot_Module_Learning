package com.yz.springboot_easy_excel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User  implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    @ExcelProperty(value = {"ID"}, index = 0)
    private Integer id;

    @ExcelProperty(value = {"neme"}, index = 1)
    private String name;

    @ExcelProperty(value = {"性别"}, index = 2)
    private String sex;

    @ExcelProperty(value = {"创建时间"}, index = 3)
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}