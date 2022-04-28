package com.kevin.mall.service.impl;

import com.kevin.mall.dao.ProductDao;
import com.kevin.mall.model.Product;
import com.kevin.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}