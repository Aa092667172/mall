package com.kevin.mall.dao.impl;

import com.kevin.mall.dao.ProductDao;
import com.kevin.mall.dto.ProductQueryParams;
import com.kevin.mall.dto.ProductRequest;
import com.kevin.mall.model.Product;
import com.kevin.mall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {

        Map<String, Object> map = new HashMap<>();

        String sqlBuilder = "select count(*) " +
                " from product where 1 = 1 " +
                addFilteringSql(map, productQueryParams);

        return namedParameterJdbcTemplate
                .queryForObject(sqlBuilder, map, Integer.class);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        var sqlBuilder = new StringBuilder("select product_id,product_name, category, image_url, price, stock," +
                " description, created_date, last_modified_date " +
                "from product where 1 = 1 ");

        Map<String, Object> map = new HashMap<>();

        sqlBuilder.append(addFilteringSql(map, productQueryParams));

        var orderBy = productQueryParams.getOrderBy();
        var sort = productQueryParams.getSort();

        sqlBuilder.append(" order by ")
                .append(orderBy)
                .append(" ")
                .append(sort);

        sqlBuilder.append(" limit :limit offset :offset ");

        map.put("limit", productQueryParams.getLimit());
        map.put("offset", productQueryParams.getOffset());

        return namedParameterJdbcTemplate.query(sqlBuilder.toString(), map, new ProductRowMapper());
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "select product_id,product_name, category, image_url, price, stock," +
                " description, created_date, last_modified_date " +
                "from product where product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        return productList.stream().findFirst().orElse(null);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "insert into product(product_name, category, image_url, price, stock," +
                " description, created_date, last_modified_date) " +
                " values (:productName , :category, :imageUrl, :price, :stock, :description, " +
                " :created_date, :lastModifiedDate )";
        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        Date now = new Date();
        map.put("created_date", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::intValue)
                .orElse(0);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "update product set product_name =:productName , category = :category , image_url = :imageUrl, price =:price," +
                " stock = :stock, description=:description,last_modified_date = :lastModifiedDate " +
                " where product_id = :productId ";

        Map<String, Object> map = new HashMap<>();

        map.put("productId", productId);

        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProduct(Integer productId) {
        String sql = "delete from product where product_id= :productId ";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    private StringBuilder addFilteringSql(Map<String, Object> map, ProductQueryParams productQueryParams) {
        var appendSql = new StringBuilder();
        var category = productQueryParams.getCategory();
        var search = productQueryParams.getSearch();

        if (Objects.nonNull(category)) {
            appendSql.append(" and category = :category ");
            map.put("category", category.name());
        }

        if (Objects.nonNull(search)) {
            appendSql.append(" and product_name like :search ");
            map.put("search", "%" + search + "%");
        }
        return appendSql;
    }
}
