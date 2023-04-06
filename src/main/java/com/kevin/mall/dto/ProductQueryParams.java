package com.kevin.mall.dto;

import com.kevin.mall.constant.ProductCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用於商品查詢",value = "商品查詢物件")
public class ProductQueryParams {
    @ApiModelProperty(value = "商品種類",example="CAR")
    private ProductCategory category;
    @ApiModelProperty(value = "商品關鍵字",example="蘋果")

    private String search;
    @ApiModelProperty(value = "排序依據",example="created_date")
    private String orderBy;
    @ApiModelProperty(value = "排序方式",example="desc")
    private String sort;
    @ApiModelProperty(value = "分頁資料",example="5")
    private Integer limit;
    @ApiModelProperty(value = "跳過幾筆",example="0")
    private Integer offset;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "ProductQueryParams{" +
                "category=" + category +
                ", search='" + search + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", sort='" + sort + '\'' +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
