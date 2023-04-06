package com.kevin.mall.controller;

import com.kevin.mall.constant.ProductCategory;
import com.kevin.mall.dto.ProductQueryParams;
import com.kevin.mall.dto.ProductRequest;
import com.kevin.mall.model.Product;
import com.kevin.mall.service.ProductService;
import com.kevin.mall.util.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Validated
@RestController
@CrossOrigin("http://localhost:8001/")
@Api(value = "ProductController", tags = "mall商品api")
@DependsOn
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    @ApiOperation(value = "取得多個商品資料")
    @ApiResponse(code = 200, message = "success")
    public ResponseEntity<Page<Product>> getProduct(
            //查詢條件
            @ApiParam(value = "商品種類",defaultValue = "CAR")
            @RequestParam(required = false) ProductCategory category,
            @ApiParam("關鍵字")
            @RequestParam(required = false) String search,
            @ApiParam(value = "排序依據", defaultValue = "created_date")
            @RequestParam(defaultValue = "created_date") String orderBy,
            //排序
            @ApiParam(value = "排序方式", defaultValue = "desc")
            @RequestParam(defaultValue = "desc") String sort,
            //分頁
            @ApiParam(value = "分頁商品", defaultValue = "5")
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @ApiParam(value = "略過前幾個商品", defaultValue = "0")
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {

        var productQueryParams = new ProductQueryParams();

        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        List<Product> list = productService.getProducts(productQueryParams);

        Integer total = productService.countProduct(productQueryParams);

        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(list);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/products/{productId}")
    @ApiOperation(value = "取得單一商品資料")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查詢成功", response = Product.class),
            @ApiResponse(code = 404, message = "查無此商品", response = Product.class)
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "商品id") @PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.of(Optional.ofNullable(product));
    }

    @PostMapping("/products")
    @ApiOperation(value = "建立商品")
    @ApiResponses({
            @ApiResponse(code = 201, message = "建立成功", response = Product.class)
    })
    public ResponseEntity<Product> createProduct(@ApiParam(value = "商品物件") @RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);
        return status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    @ApiOperation(value = "修改商品")
    @ApiResponses({
            @ApiResponse(code = 404, message = "查無此商品", response = Product.class),
            @ApiResponse(code = 200, message = "修改商品成功", response = Product.class)
    })
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {
        //檢查是否存在
        Product product = productService.getProductById(productId);
        if (Objects.isNull(product)) {
            return status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateProduct(productId, productRequest);

        Product updateProduct = productService.getProductById(productId);

        return ok(updateProduct);
    }
    @ApiOperation(value = "刪除單一商品")
    @ApiResponses({
            @ApiResponse(code = 204, message = "無商品內容", response = Product.class)
    })
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return status(HttpStatus.NO_CONTENT).build();
    }
}
