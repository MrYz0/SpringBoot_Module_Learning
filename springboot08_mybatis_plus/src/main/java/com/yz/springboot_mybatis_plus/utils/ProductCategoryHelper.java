package com.yz.springboot_mybatis_plus.utils;

import com.yz.springboot_mybatis_plus.domain.ProductCategory;

import java.util.ArrayList;
import java.util.List;


public class ProductCategoryHelper {
    public static List<ProductCategory> treeNode(List<ProductCategory> productCategoryList) {
        List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
        for (ProductCategory productCategory : productCategoryList) {
            if (productCategory.getParentId().longValue() == 0){
                productCategories.add(findChildren(productCategory,productCategoryList));
            }
        }
        return productCategories;
    }

    private static ProductCategory findChildren(ProductCategory productCategory, List<ProductCategory> productCategoryList) {
        productCategory.setChildren(new ArrayList<ProductCategory>());
        for (ProductCategory category : productCategoryList) {
            if (productCategory.getId().longValue() == category.getParentId().longValue()){
                if (productCategory.getChildren() == null){
                    productCategory.setChildren(new ArrayList<>());
                }
                productCategory.getChildren().add(findChildren(category,productCategoryList));
            }
        }
        return productCategory;
    }
}
