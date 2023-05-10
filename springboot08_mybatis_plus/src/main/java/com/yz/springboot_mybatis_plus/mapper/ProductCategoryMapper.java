package com.yz.springboot_mybatis_plus.mapper;

import com.yz.springboot_mybatis_plus.domain.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yangzhou
* @description 针对表【product_category】的数据库操作Mapper
* @createDate 2023-05-09 15:25:37
* @Entity com.yz.springboot_mybatis_plus.domain.ProductCategory
*/
@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

}




