package com.kevin.mall.dao;

import com.kevin.mall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
