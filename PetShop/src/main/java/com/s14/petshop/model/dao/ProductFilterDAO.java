package com.s14.petshop.model.dao;

import com.s14.petshop.model.dtos.product.ProductFilterDTO;
import com.s14.petshop.model.dtos.product.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductFilterDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Integer> filterProducts(ProductFilterDTO dto) {
        double minPrice = dto.getMinPrice();
        double maxPrice = dto.getMaxPrice();
        int brand_id = dto.getBrandId();
        int discount_id = dto.getDiscountId();
        int subcategoryId = dto.getSubcategoryId();
        int rating = dto.getRating();

        StringBuilder sql = new StringBuilder("SELECT DISTINCT p.id FROM products AS p " +
                "JOIN subcategories AS sc ON p.subcategory_id = sc.id ");

        if(rating > 0){
            sql.append("JOIN reviews AS r ON p.id = r.product_id " +
                    "WHERE r.rating =" + rating + " AND ");
        }
        else{
            sql.append(" WHERE ");
        }

        sql.append(" sc.id =" + subcategoryId + " AND p.price BETWEEN " + minPrice + " AND " + maxPrice);
        if (brand_id > 0) {
            sql.append(" AND p.brand_id = " + brand_id);
        }

        if (discount_id > 0){
            sql.append(" AND p.discount_id = " + discount_id);
        }
        System.out.println(sql);

        List<Integer> result = new ArrayList<>();
        jdbcTemplate.query(sql.toString(), resultSet ->{
            result.add(resultSet.getInt("id"));
            while(resultSet.next()){
                result.add(resultSet.getInt("id"));
            }
        });
        return result;
    }



}
