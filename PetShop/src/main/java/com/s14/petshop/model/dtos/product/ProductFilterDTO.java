package com.s14.petshop.model.dtos.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor

public class ProductFilterDTO {

    @NotNull
    private double minPrice;
    @NotNull
    private double maxPrice;
    private int brandId;
    private int discountId;

    @NotBlank
    private int subcategoryId;
    private int rating;
}
