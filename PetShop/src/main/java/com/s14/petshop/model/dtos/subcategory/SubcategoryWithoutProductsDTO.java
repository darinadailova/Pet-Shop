package com.s14.petshop.model.dtos.subcategory;

import com.s14.petshop.model.dtos.category.CategoryWithoutSubsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor

public class SubcategoryWithoutProductsDTO {
    @NotNull
    private int id;

    @NotBlank
    private String name;

    @NotNull
    private CategoryWithoutSubsDTO category;
}
