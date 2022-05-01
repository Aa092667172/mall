package com.kevin.mall.controller;

import com.kevin.mall.model.Product;
import com.kevin.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);
        return Optional.ofNullable(product).map(p -> {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(p);
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
