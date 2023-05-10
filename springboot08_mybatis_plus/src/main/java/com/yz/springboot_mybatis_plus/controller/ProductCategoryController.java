package com.yz.springboot_mybatis_plus.controller;

import com.yz.springboot_mybatis_plus.domain.ProductCategory;
import com.yz.springboot_mybatis_plus.service.ProductCategoryService;
import com.yz.springboot_mybatis_plus.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("list")
    public Result listProducts(){
        List<ProductCategory> productCategoryList = productCategoryService.treeNode();
    return Result.ok(productCategoryList);
    }
}
