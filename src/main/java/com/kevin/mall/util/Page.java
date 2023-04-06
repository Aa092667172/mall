package com.kevin.mall.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
@ApiModel(description = "用於裝載分頁商品資料容器",value = "分頁物件")
public class Page<T> {
    @ApiModelProperty(value = "分頁資料",example="5")
    private Integer limit;
    @ApiModelProperty(value = "跳過幾筆",example="0")
    private Integer offset;
    @ApiModelProperty(value = "筆數總額",example="10")
    private Integer total;
    private List<T> results;

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
