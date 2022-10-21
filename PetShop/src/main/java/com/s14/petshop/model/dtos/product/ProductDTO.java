package com.s14.petshop.model.dtos.product;

import com.s14.petshop.model.beans.Brand;
import com.s14.petshop.model.beans.Discount;
import com.s14.petshop.model.beans.Subcategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductDTO {

    private int id;
    private String name;
    private double price;
    private String info;
    private Subcategory subcategory;
    private Brand brand;
    private Discount discount;
}
