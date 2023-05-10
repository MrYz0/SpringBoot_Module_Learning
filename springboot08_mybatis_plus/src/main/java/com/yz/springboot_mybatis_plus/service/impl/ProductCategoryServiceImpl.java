package com.yz.springboot_mybatis_plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yz.springboot_mybatis_plus.domain.ProductCategory;
import com.yz.springboot_mybatis_plus.service.ProductCategoryService;
import com.yz.springboot_mybatis_plus.mapper.ProductCategoryMapper;
import com.yz.springboot_mybatis_plus.utils.ProductCategoryHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yangzhou
* @description 针对表【product_category】的数据库操作Service实现
* @createDate 2023-05-09 15:25:37
*/
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory>
    implements ProductCategoryService{


    /**
     * 产品类别树
     * 1、所有产品类别，查询出来
     * 2、List 将顶层的菜单 查询出来，放到集合中
     * 3、 递归的查找子菜单
     *
     * @return {@link List}<{@link ProductCategory}>
     */
    @Override
    public List<ProductCategory> treeNode() {
        List<ProductCategory> productCategoryList = baseMapper.selectList(null);
        List<ProductCategory> resultList = ProductCategoryHelper.treeNode(productCategoryList);
        return resultList;
    }

}



