package com.kevin.mall.controller;

import com.kevin.mall.dto.ProductRequest;
import com.kevin.mall.model.Product;
import com.kevin.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        return Optional.ofNullable(product)
                .map(status(HttpStatus.OK)::body)
                .orElse(status(HttpStatus.NOT_FOUND).build());
    }
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);
        return status(HttpStatus.CREATED).body(product);
    }
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){
        //檢查是否存在
        Product product = productService.getProductById(productId);
        if(Objects.isNull(product)){
            return status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateProduct(productId,productRequest);

        Product updateProduct = productService.getProductById(productId);

        return status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProduct(productId);
        return status(HttpStatus.NO_CONTENT).build();
    }
}
