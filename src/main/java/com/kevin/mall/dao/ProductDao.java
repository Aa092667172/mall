package com.kevin.mall.dao;

import com.kevin.mall.constant.ProductCategory;
import com.kevin.mall.dto.ProductRequest;
import com.kevin.mall.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts(ProductCategory productCategory,String search);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProduct(Integer productId);

}
