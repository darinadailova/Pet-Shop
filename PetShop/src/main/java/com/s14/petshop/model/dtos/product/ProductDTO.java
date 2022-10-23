package com.s14.petshop.model.dtos.product;

import com.s14.petshop.model.beans.Brand;
import com.s14.petshop.model.beans.Discount;
import com.s14.petshop.model.beans.Subcategory;
import com.s14.petshop.model.dtos.brand.BrandDTO;
import com.s14.petshop.model.dtos.discount.DiscountAddDTO;
import com.s14.petshop.model.dtos.discount.DiscountDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryDTO;
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
    private SubcategoryDTO subcategory;
    private BrandDTO brand;
    private DiscountDTO discount;
}
