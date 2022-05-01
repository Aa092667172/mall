package com.kevin.mall.service;

import com.kevin.mall.dto.ProductRequest;
import com.kevin.mall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);
}
