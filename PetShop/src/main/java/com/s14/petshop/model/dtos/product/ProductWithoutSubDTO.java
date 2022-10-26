package com.s14.petshop.model.dtos.product;

import com.s14.petshop.model.dtos.brand.BrandResponseDTO;
import com.s14.petshop.model.dtos.discount.DiscountResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor

public class ProductWithoutSubDTO {

    @NotNull
    private int id;

    @NotBlank
    private String name;

    @NotNull
    private double price;

    @NotBlank
    private String info;

    @NotNull
    private BrandResponseDTO brand;

    private DiscountResponseDTO discount;
}
