package com.s14.petshop.model.dtos.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductAddDTO {
    private int id;
    private String name;
    private double price;
    private String info;
    private int subcategory_id;
    private int brand_id;
    private int discount_id;
}