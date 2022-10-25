package com.s14.petshop.model.dtos.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor

public class ProductAddDTO {

    @NotBlank
    @Length(min = 2, max = 45, message =  "Product name must be between 2 and 45 characters")
    private String name;

    @NotNull
    @Min(1)
    private double price;

    @NotBlank
    private String info;

    @NotNull
    private int subcategory_id;

    @NotNull
    private int brand_id;

    @NotNull
    private int discount_id;
}