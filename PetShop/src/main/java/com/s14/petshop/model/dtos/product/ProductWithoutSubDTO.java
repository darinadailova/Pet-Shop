package com.s14.petshop.model.dtos.product;

import com.s14.petshop.model.dtos.brand.BrandDTO;
import com.s14.petshop.model.dtos.discount.DiscountDTO;
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
    private BrandDTO brand;

    @NotNull
    private DiscountDTO discount;
}
