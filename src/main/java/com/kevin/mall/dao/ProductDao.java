package com.kevin.mall.dao;

import com.kevin.mall.dto.ProductRequest;
import com.kevin.mall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProduct(Integer productId);

}
