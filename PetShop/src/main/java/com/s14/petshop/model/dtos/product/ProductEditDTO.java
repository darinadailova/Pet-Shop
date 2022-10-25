package com.s14.petshop.model.dtos.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class ProductEditDTO {
    private String name;
    private double price;
    private String info;
}
