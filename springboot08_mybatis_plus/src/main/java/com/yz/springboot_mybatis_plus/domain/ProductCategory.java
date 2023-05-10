package com.yz.springboot_mybatis_plus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName product_category
 */
@TableName(value ="product_category")
@Data
public class ProductCategory implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 上级分类的编号：0表示一级分类
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    @TableField(exist = false)
    private List<ProductCategory> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}