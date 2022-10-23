package com.s14.petshop.model.dtos.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductAddDTO {
    private String name;
    private double price;
    private String info;
    private int subcategory_id;
    private int brand_id;
    private int discount_id;
}
