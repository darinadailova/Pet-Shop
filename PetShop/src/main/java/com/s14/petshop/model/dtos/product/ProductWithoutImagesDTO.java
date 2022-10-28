package com.s14.petshop.model.dtos.product;

import com.s14.petshop.model.dtos.brand.BrandResponseDTO;
import com.s14.petshop.model.dtos.discount.DiscountResponseDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryWithoutProductsDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductWithoutImagesDTO {

    @NotNull
    private int id;

    @NotBlank
    private String name;

    @NotNull
    private double price;

    @NotBlank
    private String info;

    @NotNull
    private SubcategoryWithoutProductsDTO subcategory;

    @NotNull
    private BrandResponseDTO brand;

    private DiscountResponseDTO discount;
}
