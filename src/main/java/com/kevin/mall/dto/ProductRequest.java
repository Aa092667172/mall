package com.kevin.mall.dto;

import com.kevin.mall.constant.ProductCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "用於商品建立",value = "商品建立物件")
public class ProductRequest {
    @NotNull
    @ApiModelProperty(value = "商品名稱",example="蘋果")
    private String productName;
    @NotNull
    @ApiModelProperty(value = "種類",required = true,example="CAR")
    private ProductCategory category;
    @NotNull
    @ApiModelProperty(value = "商品圖片",example = "https://cdn.pixabay.com/photo/2021/07/30/04/17/orange-6508617_1280.jpg")
    private String imageUrl;
    @NotNull
    @ApiModelProperty(value = "商品金額",required = true,example="100")
    private Integer price;
    @NotNull
    @ApiModelProperty(value = "商品庫存",required = true,example="20")
    private Integer stock;
    @ApiModelProperty(value = "商品描述",example="漂亮精美的汽車")
    private String description;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "productName='" + productName + '\'' +
                ", category=" + category +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                '}';
    }
}
