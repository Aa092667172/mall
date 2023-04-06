package com.kevin.mall.model;

import com.kevin.mall.constant.ProductCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.util.Date;
@ApiModel(description = "商品對應資料庫物件",value = "商品對象")
public class Product {
    @ApiModelProperty(value = "商品id",example="1",name = "productId")
    @ApiParam(defaultValue = "1")
    private Integer productId;
    @ApiModelProperty(value = "商品名稱",required = true,example="汽車")
    private String productName;
    @ApiModelProperty(value = "種類",required = true,example="CAR")
    private ProductCategory category;
    @ApiModelProperty(value = "商品圖片",example = "https://cdn.pixabay.com/photo/2021/07/30/04/17/orange-6508617_1280.jpg")
    private String imageUrl;
    @ApiModelProperty(value = "商品金額",required = true,example ="100")
    private Integer price;
    @ApiModelProperty(value = "商品庫存",required = true,example ="20")
    private Integer stock;
    @ApiModelProperty(value = "商品描述",example ="超酷汽車")
    private String description;
    @ApiModelProperty(value = "建立時間",example ="2022-03-19 17:00:00")
    private Date  createDate;
    @ApiModelProperty(value = "修改時間",example ="2022-03-19 17:00:00")
    private Date  lastModifiedDate;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
