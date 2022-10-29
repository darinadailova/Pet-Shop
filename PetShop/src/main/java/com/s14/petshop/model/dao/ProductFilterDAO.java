package com.s14.petshop.model.dao;

import com.s14.petshop.model.dtos.product.ProductFilterDTO;
import com.s14.petshop.model.dtos.product.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    "WHERE r.rating = ? AND ");
        }
        else{
            sql.append(" WHERE ");
        }

        sql.append(" sc.id = ? AND p.price BETWEEN ? AND ?");
        if (brand_id > 0) {
            sql.append(" AND p.brand_id = ?");
        }

        if (discount_id > 0){
            sql.append(" AND p.discount_id = ?");
        }
        System.out.println(sql);

        List<Integer> result = new ArrayList<>();
        jdbcTemplate.query(sql.toString(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                if(rating > 0 ){
                    ps.setInt(1,rating);
                    ps.setInt(2,subcategoryId);
                    ps.setDouble(3,minPrice);
                    ps.setDouble(4,maxPrice);
                }
                else{
                    ps.setInt(1,subcategoryId);
                    ps.setDouble(2,minPrice);
                    ps.setDouble(3,maxPrice);
                }

                if(rating > 0 && brand_id > 0){
                    ps.setInt(5,brand_id);
                }
                else if(brand_id > 0){
                    ps.setInt(4,brand_id);
                }

                if(rating > 0 && brand_id > 0 && discount_id > 0){
                    ps.setInt(6,discount_id);
                }
                else if(rating > 0 && discount_id > 0){
                    ps.setInt(5,discount_id);
                }
                else if(brand_id > 0 && discount_id > 0){
                    ps.setInt(5,discount_id);
                }
                else if(discount_id > 0){
                    ps.setInt(4,discount_id);
                }
                System.out.println(ps.toString());
            }
        }, resultSet -> {
            result.add(resultSet.getInt("id"));
            while (resultSet.next()) {
                result.add(resultSet.getInt("id"));
            }
        });
        return result;
    }



}
