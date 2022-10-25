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

public class ProductEditDTO {

    @NotBlank
    private String name;

    @NotNull
    private double price;

    @NotBlank
    private String info;
}
