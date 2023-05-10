package com.yz.springboot_mybatis_plus.service;

import com.yz.springboot_mybatis_plus.domain.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author yangzhou
* @description 针对表【product_category】的数据库操作Service
* @createDate 2023-05-09 15:25:37
*/
public interface ProductCategoryService extends IService<ProductCategory> {


    List<ProductCategory> treeNode();


}
