package com.s14.petshop.model.dtos.product;

import com.s14.petshop.model.dtos.brand.BrandResponseDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryWithoutProductsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor

public class ProductWithoutDiscountDTO {

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
}
