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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class ProductDTO {

    @NotNull
    private int id;

    @NotBlank
    private String name;

    @NotNull
    private double price;

    @NotBlank
    private String info;

    @NotNull
    private SubcategoryDTO subcategory;

    @NotNull
    private BrandDTO brand;

    @NotNull
    private DiscountDTO discount;
}
