package com.s14.petshop.model.dtos.subcategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class SubcategoryAddDTO {
    private String name;
    private int categoryId;
}
